function initiate(inputString) {
	resetErrorPanels();
	var tree = getAntrlParseTree(inputString);
	var visitor = getAntlrVisitor();
	visitor.visitForm = function (ctx) {
		ast = ctx.FormNode;
		if (performAstChecks()) {
			renderQuestions();
			setHTMLEventHandlers();
			refreshGUI();
		}
		else {
			throwError(0, "Form checks failed");
		}
	};
	tree.accept(visitor);
}

function getAntrlParseTree(input) {
	var antlr4 = require('antlr4/index');
	var QLGrammarLexer = require('js/antlrGen/QLGrammarLexer');
	var QLGrammarParser = require('js/antlrGen/QLGrammarParser');

	var characters = new antlr4.InputStream(input);
	var lexer = new QLGrammarLexer.QLGrammarLexer(characters);
	var tokens = new antlr4.CommonTokenStream(lexer);
	var parserAntlr = new QLGrammarParser.QLGrammarParser(tokens);
	parserAntlr.buildParseTrees = true;

	var ErrorListener = function () {
		antlr4.error.ErrorListener.call(this);
		return this;
	};
	ErrorListener.prototype = Object.create(antlr4.error.ErrorListener.prototype);
	ErrorListener.prototype.constructor = ErrorListener;
	ErrorListener.prototype.syntaxError = function (rec, sym, line, col, msg, e) {
		throwError(line, "Parse error: " + msg);
	};

	lexer.removeErrorListeners();
	parserAntlr.removeErrorListeners();
	lexer.addErrorListener(new ErrorListener());
	parserAntlr.addErrorListener(new ErrorListener());


	return parserAntlr.form();
}

function getAntlrVisitor() {
	var QLGrammarVisitor = require('js/antlrGen/QLGrammarVisitor');
	var Visitor = function () {
		QLGrammarVisitor.QLGrammarVisitor.call(this);
		return this;
	};
	Visitor.prototype = Object.create(QLGrammarVisitor.QLGrammarVisitor.prototype);
	return new Visitor();
}

function performAstChecks() {
	var texts = new Set();
	var labels = new Set();

	var noErrors = true;

	ast.transverseAST(
		(questionNode) => {
			if (labels.has(questionNode.label)) {
				throwError(questionNode.line, "Question error: Question label '" + questionNode.label + "' is already defined");
				noErrors = false;
			}
			if (texts.has(questionNode.text)) {
				throwWarning(questionNode.line, "Question warning: Text '" + questionNode.text + "' for question '" + questionNode.label + "' is already defined");
			}
			if (questionNode instanceof ComputedQuestionNode) {
				if (questionNode.computedExpr.compute() === undefined) {
					throwError(questionNode.computedExpr.line, "Type error: Computed expression '" + questionNode.computedExpr.toString() + "' is undefined");
					noErrors = false;
				}
				else if (questionNode.type.toString() !== typeof questionNode.computedExpr.compute()) {
					throwError(questionNode.computedExpr.line, "Type error: Computed expression '" + questionNode.computedExpr.toString() + "' must evaluate to " + questionNode.type.getTypeString());
					noErrors = false;
				}
			}
			labels.add(questionNode.label);
			texts.add(questionNode.text);
		},
		(conditionNode) => {
			if ("boolean" !== typeof conditionNode.condition.compute()) {
				throwError(conditionNode.line, "Type error: Condition '" + conditionNode.condition.toString() + "' is not boolean");
				noErrors = false;
			}
		}
	);

	if (noErrors) {
		noErrors = checkDependencies();
	}

	return noErrors;
}

function checkDependencies() {
	var map = [];

	ast.transverseAST(
		(questionNode) => {
			if (questionNode instanceof ComputedQuestionNode) {
				map[questionNode.label] = questionNode.computedExpr.getLabels();
			}
		});

	var madeChange = false;

	do {
		madeChange = false;
		for (var elementA in map) {
			if (map.hasOwnProperty(elementA)) {
				for (let elementB of map[elementA]) {
					if (map[elementB] !== undefined) {
						if (map[elementB].indexOf(elementA) !== -1) {
							throwError(ast.getQuestion(elementA).line, "Cyclic dependencies detected between question '" + elementA + "' and question '" + elementB + "'");
							return false;
						}
						for (let elementC of map[elementB]) {
							if (map[elementA].indexOf(elementC) === -1) {
								map[elementA].push(elementC);
								madeChange = true;
							}
						}

					}
				}
			}
		}
	}
	while (madeChange);

	return true;
}

function throwError(line, errorMsg) {
	renderDebugMessage("error", line, errorMsg);
}

function throwWarning(line, warningMsg) {
	renderDebugMessage("warning", line, warningMsg);
}