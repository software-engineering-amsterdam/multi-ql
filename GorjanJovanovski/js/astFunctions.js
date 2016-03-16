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
	var identifierMap = [];

	var noErrors = true;

	ast.transverseAST(
		(questionNode) => {

			checkQuestionTextDuplicate(questionNode, textSet);

			if (isQuestionIdentifierDuplicate(questionNode, identifierMap)) {
				noErrors = false;
			}
			else {
				identifierMap[questionNode.label] = questionNode.getTypeString();
			}

			if (questionNode instanceof ComputedQuestionNode) {
				if (!isExpressionDefined(questionNode.line, questionNode.computedExpr, environment)) {
					noErrors = false;
				}
				else if (!isExpressionTypeMatch(questionNode.line, questionNode.type.toString(), questionNode.computedExpr, environment)) {
					noErrors = false;
				}
			}

			textSet.add(questionNode.text);
		},
		(conditionNode) => {
			if (!isExpressionDefined(conditionNode.line, conditionNode.condition, environment)) {
				noErrors = false;
			}
			else if (!isExpressionTypeMatch(conditionNode.line, "boolean", conditionNode.condition, environment)) {
				noErrors = false;
			}
		}
	);

	if (noErrors) {
		noErrors = checkDependencies(ast);
	}

	return noErrors;
}

function isQuestionIdentifierDuplicate(questionNode, identifierMap) {
	if (identifierMap[questionNode.label] !== undefined && identifierMap[questionNode.label] !== questionNode.getTypeString()) {
		throwError(questionNode.line, "Question error: Question identifier '" + questionNode.label + "' of type '" + questionNode.getTypeString() + "' is already defined as of another type");
		return true;
	}
	return false;
}

function checkQuestionTextDuplicate(questionNode, textSet) {
	if (textSet.has(questionNode.text)) {
		throwWarning(questionNode.line, "Question warning: Text '" + questionNode.text + "' for question '" + questionNode.label + "' is already defined");
	}
}

function isExpressionDefined(line, expression, environment) {
	if (expression.compute(environment) === undefined) {
		throwError(line, "Type error: Computed expression '" + expression.toString() + "' is undefined");
		return false;
	}
	return true;
}

function isExpressionTypeMatch(line, type, expression, environment) {
	if (type !== typeof expression.compute(environment)) {
		throwError(line, "Type error: Computed expression '" + expression.toString() + "' must evaluate to " + type);
		return false;
	}
	return true;
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