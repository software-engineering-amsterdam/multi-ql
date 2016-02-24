// Generated from MyGrammer.g4 by ANTLR 4.5.2
// jshint ignore: start
var antlr4 = require('antlr4/index');

// This class defines a complete generic visitor for a parse tree produced by MyGrammerParser.

function MyGrammerVisitor() {
	antlr4.tree.ParseTreeVisitor.call(this);
	return this;
}

MyGrammerVisitor.prototype = Object.create(antlr4.tree.ParseTreeVisitor.prototype);
MyGrammerVisitor.prototype.constructor = MyGrammerVisitor;

// Visit a parse tree produced by MyGrammerParser#form.
MyGrammerVisitor.prototype.visitForm = function(ctx) {
};


// Visit a parse tree produced by MyGrammerParser#block.
MyGrammerVisitor.prototype.visitBlock = function(ctx) {
};


// Visit a parse tree produced by MyGrammerParser#blockstmt.
MyGrammerVisitor.prototype.visitBlockstmt = function(ctx) {
};


// Visit a parse tree produced by MyGrammerParser#question.
MyGrammerVisitor.prototype.visitQuestion = function(ctx) {
};


// Visit a parse tree produced by MyGrammerParser#computedquestion.
MyGrammerVisitor.prototype.visitComputedquestion = function(ctx) {
};


// Visit a parse tree produced by MyGrammerParser#ifstmt.
MyGrammerVisitor.prototype.visitIfstmt = function(ctx) {
};


// Visit a parse tree produced by MyGrammerParser#expr.
MyGrammerVisitor.prototype.visitExpr = function(ctx) {
};



exports.MyGrammerVisitor = MyGrammerVisitor;