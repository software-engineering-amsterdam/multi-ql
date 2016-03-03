function initiate(inputString) {
	resetGUI();
	var tree = getAntrlParseTree(inputString);
	var visitor = getAntlrVisitor();
	visitor.visitForm = function (ctx) {
		var ast = ctx.FormNode;
		var environment = new Environment();
		ast.setEnvironment(environment);
		if (performAstChecks(ast, environment)) {
			renderQuestions(ast, environment);
			refreshGUI(ast, environment);
			setOnClickListeners(ast);
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

function performAstChecks(ast, environment) {
	var textSet = new Set();
	var labelSet = new Set();

	var noErrors = true;

	ast.transverseAST(
		(questionNode) => {

			isQuestionTextDuplicate(questionNode, textSet);

			if (isQuestionLabelDuplicate(questionNode, labelSet)) {
				noErrors = false;
			}

			if (questionNode instanceof ComputedQuestionNode) {
				if (isExpressionUndefined(questionNode.line, questionNode.computedExpr, environment)) {
					noErrors = false;
				}
				if (isExpressionTypeMismatch(questionNode.line, questionNode.type.toString(), questionNode.computedExpr, environment)) {
					noErrors = false;
				}
			}

			textSet.add(questionNode.text);
			labelSet.add(questionNode.label);
		},
		(conditionNode) => {
			if (isExpressionUndefined(conditionNode.line, conditionNode.condition, environment)) {
				noErrors = false;
			}
			if (isExpressionTypeMismatch(conditionNode.line, "boolean", conditionNode.condition, environment)) {
				noErrors = false;
			}
		}
	);

	if (noErrors) {
		noErrors = checkDependencies(ast);
	}

	return noErrors;
}

function isQuestionLabelDuplicate(questionNode, labelSet) {
	if (labelSet.has(questionNode.label)) {
		throwWarning(questionNode.line, "Question warning: Text '" + questionNode.text + "' for question '" + questionNode.label + "' is already defined");
		return true;
	}
	return false;
}

function isQuestionTextDuplicate(questionNode, textSet) {
	if (textSet.has(questionNode.text)) {
		throwError(questionNode.line, "Question error: Question label '" + questionNode.label + "' is already defined");
		return true;
	}
	return false;
}

function isExpressionUndefined(line, expression, environment) {
	if (expression.compute(environment) === undefined) {
		throwError(line, "Type error: Computed expression '" + expression.toString() + "' is undefined");
		return true;
	}
	return false;
}

//FIX THIS
function isExpressionTypeMismatch(line, type, expression, environment) {
	if (type !== typeof expression.compute(environment)) {
		throwError(line, "Type error: Computed expression '" + expression.toString() + "' must evaluate to " + type);
		return true;
	}
	return false;
}

function checkDependencies(ast) {
	var map = [];

	ast.transverseAST(
		(questionNode) => {
			if (questionNode instanceof ComputedQuestionNode) {
				map[questionNode.label] = questionNode.computedExpr.getLabelsInExpression();
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
							throwError(0, "Cyclic dependencies detected between question '" + elementA + "' and question '" + elementB + "'");
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