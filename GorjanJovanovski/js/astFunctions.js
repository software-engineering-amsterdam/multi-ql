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
			throwError(1, "Form checks failed");
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
	var parserANTLR = new QLGrammarParser.QLGrammarParser(tokens);
	parserANTLR.buildParseTrees = true;

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
	parserANTLR.removeErrorListeners();
	lexer.addErrorListener(new ErrorListener());
	parserANTLR.addErrorListener(new ErrorListener());


	return parserANTLR.form();
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
				throwError(questionNode.line, "Label '" + questionNode.label + "' is already defined");
				noErrors = false;
			}
			if (texts.has(questionNode.text)) {
				throwWarning(questionNode.line, "Text '" + questionNode.text + "' is already defined");
			}
			if (questionNode instanceof ComputedQuestionNode) {
				if (questionNode.computedExpr.compute() === undefined) {
					throwError(questionNode.computedExpr.line, "Computed expression '" + questionNode.computedExpr.toString() + "' is undefined");
					noErrors = false;
				}
				else if (questionNode.type.getTypeString() !== typeof questionNode.computedExpr.compute()) {
					throwError(questionNode.computedExpr.line, "Computed expression '" + questionNode.computedExpr.toString() + "' must evaluate to " + questionNode.type.getTypeString());
					noErrors = false;
				}
			}
			labels.add(questionNode.label);
			texts.add(questionNode.text);
		},
		(conditionNode) => {
			var evalResult = conditionNode.condition.compute();
			if (typeof evalResult !== "boolean") {
				throwError(conditionNode.line, "Condition '" + conditionNode.condition.toString() + "' is not boolean");
				noErrors = false;
			}
		}
	);

	return noErrors;
}

function throwError(line, errorMsg) {
	renderDebugMessage("error", line, errorMsg);
}

function throwWarning(line, warningMsg) {
	renderDebugMessage("warning", line, warningMsg);
}