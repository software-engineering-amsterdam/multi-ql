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


// Visit a parse tree produced by MyGrammerParser#queries.
MyGrammerVisitor.prototype.visitQueries = function(ctx) {
};


// Visit a parse tree produced by MyGrammerParser#question.
MyGrammerVisitor.prototype.visitQuestion = function(ctx) {
};


// Visit a parse tree produced by MyGrammerParser#ifstmt.
MyGrammerVisitor.prototype.visitIfstmt = function(ctx) {
};


// Visit a parse tree produced by MyGrammerParser#decimalLiteral.
MyGrammerVisitor.prototype.visitDecimalLiteral = function(ctx) {
};


// Visit a parse tree produced by MyGrammerParser#labelNode.
MyGrammerVisitor.prototype.visitLabelNode = function(ctx) {
};


// Visit a parse tree produced by MyGrammerParser#negationExpr.
MyGrammerVisitor.prototype.visitNegationExpr = function(ctx) {
};


// Visit a parse tree produced by MyGrammerParser#opExpr.
MyGrammerVisitor.prototype.visitOpExpr = function(ctx) {
};


// Visit a parse tree produced by MyGrammerParser#booleanLiteral.
MyGrammerVisitor.prototype.visitBooleanLiteral = function(ctx) {
};


// Visit a parse tree produced by MyGrammerParser#parenthesisExpr.
MyGrammerVisitor.prototype.visitParenthesisExpr = function(ctx) {
};


// Visit a parse tree produced by MyGrammerParser#numberLiteral.
MyGrammerVisitor.prototype.visitNumberLiteral = function(ctx) {
};



exports.MyGrammerVisitor = MyGrammerVisitor;