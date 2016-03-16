function initiate(inputString) {
	resetGUI();
	var tree = getAntrlParseTree(inputString);
	var visitor = getAntlrVisitor();
	visitor.visitForm = function (ctx) {
		var ast = ctx.FormNode;
		var environment = new Environment();
		ast.setEnvironment(environment);
		if (performAstChecks(ast)) {
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