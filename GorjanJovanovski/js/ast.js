function initiate(inputString) {

	var antlr4 = require('antlr4/index');
	var QLGrammarLexer = require('QLGrammarLexer');
	var QLGrammarParser = require('QLGrammarParser');

	var characters = new antlr4.InputStream(inputString);
	var lexer = new QLGrammarLexer.QLGrammarLexer(characters);
	var tokens = new antlr4.CommonTokenStream(lexer);
	var parserANTLR = new QLGrammarParser.QLGrammarParser(tokens);

	//TODO go to other function
	var editor = ace.edit("input");
	editor.getSession().clearAnnotations();
	resetErrorPanels();

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

	parserANTLR.buildParseTrees = true;

	var tree = parserANTLR.form();
	visitParseTree(tree);
}

function visitParseTree(parseTree) {

	var QLGrammarVisitor = require('QLGrammarVisitor');

	var Visitor = function () {
		QLGrammarVisitor.QLGrammarVisitor.call(this);
		return this;
	};
	Visitor.prototype = Object.create(QLGrammarVisitor.QLGrammarVisitor.prototype);
	Visitor.prototype.visitForm = function (ctx) {
		ast = ctx.FormNode;
		if (performASTCheck()) {
			renderQuestions();
			setHandlers();
			refreshGUI();
			console.log("Generated AST: ");
			console.log(ast);
		}
	};

	var visitor = new Visitor();
	parseTree.accept(visitor);
}

function performASTCheck() {
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
			if (questionNode instanceof ComputedQuestionNode && questionNode.computedExpr.compute() === undefined) {
				throwError(questionNode.computedExpr.line, "Computed expression '" + questionNode.computedExpr.toString() + "' is undefined");
				noErrors = false;
			}
			labels.add(questionNode.label);
			texts.add(questionNode.text);
		},
		(conditionNode) => {
			var evalResult = conditionNode.condition.compute();
			if (typeof evalResult !== "boolean") {
				noErrors = false;
				throwError(conditionNode.line, "Condition '" + conditionNode.condition.toString() + "' is not boolean");
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