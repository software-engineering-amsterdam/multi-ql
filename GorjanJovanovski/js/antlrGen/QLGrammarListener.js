// Generated from C:/xampp/htdocs/Software Construction/multi-ql/GorjanJovanovski\QLGrammar.g4 by ANTLR 4.5.1
// jshint ignore: start
var antlr4 = require('antlr4/index');

// This class defines a complete listener for a parse tree produced by QLGrammarParser.
function QLGrammarListener() {
	antlr4.tree.ParseTreeListener.call(this);
	return this;
}

QLGrammarListener.prototype = Object.create(antlr4.tree.ParseTreeListener.prototype);
QLGrammarListener.prototype.constructor = QLGrammarListener;

// Enter a parse tree produced by QLGrammarParser#form.
QLGrammarListener.prototype.enterForm = function(ctx) {
};

// Exit a parse tree produced by QLGrammarParser#form.
QLGrammarListener.prototype.exitForm = function(ctx) {
};


// Enter a parse tree produced by QLGrammarParser#block.
QLGrammarListener.prototype.enterBlock = function(ctx) {
};

// Exit a parse tree produced by QLGrammarParser#block.
QLGrammarListener.prototype.exitBlock = function(ctx) {
};


// Enter a parse tree produced by QLGrammarParser#blockstmt.
QLGrammarListener.prototype.enterBlockstmt = function(ctx) {
};

// Exit a parse tree produced by QLGrammarParser#blockstmt.
QLGrammarListener.prototype.exitBlockstmt = function(ctx) {
};


// Enter a parse tree produced by QLGrammarParser#question.
QLGrammarListener.prototype.enterQuestion = function(ctx) {
};

// Exit a parse tree produced by QLGrammarParser#question.
QLGrammarListener.prototype.exitQuestion = function(ctx) {
};


// Enter a parse tree produced by QLGrammarParser#computedquestion.
QLGrammarListener.prototype.enterComputedquestion = function(ctx) {
};

// Exit a parse tree produced by QLGrammarParser#computedquestion.
QLGrammarListener.prototype.exitComputedquestion = function(ctx) {
};


// Enter a parse tree produced by QLGrammarParser#ifstmt.
QLGrammarListener.prototype.enterIfstmt = function(ctx) {
};

// Exit a parse tree produced by QLGrammarParser#ifstmt.
QLGrammarListener.prototype.exitIfstmt = function(ctx) {
};


// Enter a parse tree produced by QLGrammarParser#expr.
QLGrammarListener.prototype.enterExpr = function(ctx) {
};

// Exit a parse tree produced by QLGrammarParser#expr.
QLGrammarListener.prototype.exitExpr = function(ctx) {
};



exports.QLGrammarListener = QLGrammarListener;