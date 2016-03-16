// Generated from C:/xampp/htdocs/Software Construction/multi-ql/GorjanJovanovski\QLGrammar.g4 by ANTLR 4.5.1
// jshint ignore: start
var antlr4 = require('antlr4/index');

// This class defines a complete generic visitor for a parse tree produced by QLGrammarParser.

function QLGrammarVisitor() {
	antlr4.tree.ParseTreeVisitor.call(this);
	return this;
}

QLGrammarVisitor.prototype = Object.create(antlr4.tree.ParseTreeVisitor.prototype);
QLGrammarVisitor.prototype.constructor = QLGrammarVisitor;

// Visit a parse tree produced by QLGrammarParser#form.
QLGrammarVisitor.prototype.visitForm = function(ctx) {
};


// Visit a parse tree produced by QLGrammarParser#block.
QLGrammarVisitor.prototype.visitBlock = function(ctx) {
};


// Visit a parse tree produced by QLGrammarParser#blockstmt.
QLGrammarVisitor.prototype.visitBlockstmt = function(ctx) {
};


// Visit a parse tree produced by QLGrammarParser#question.
QLGrammarVisitor.prototype.visitQuestion = function(ctx) {
};


// Visit a parse tree produced by QLGrammarParser#computedquestion.
QLGrammarVisitor.prototype.visitComputedquestion = function(ctx) {
};


// Visit a parse tree produced by QLGrammarParser#ifstmt.
QLGrammarVisitor.prototype.visitIfstmt = function(ctx) {
};


// Visit a parse tree produced by QLGrammarParser#expr.
QLGrammarVisitor.prototype.visitExpr = function(ctx) {
};



exports.QLGrammarVisitor = QLGrammarVisitor;