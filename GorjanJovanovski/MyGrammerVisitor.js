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


// Visit a parse tree produced by MyGrammerParser#questionText.
MyGrammerVisitor.prototype.visitQuestionText = function(ctx) {
};


// Visit a parse tree produced by MyGrammerParser#questionLabel.
MyGrammerVisitor.prototype.visitQuestionLabel = function(ctx) {
};


// Visit a parse tree produced by MyGrammerParser#questionValue.
MyGrammerVisitor.prototype.visitQuestionValue = function(ctx) {
};


// Visit a parse tree produced by MyGrammerParser#ifstmt.
MyGrammerVisitor.prototype.visitIfstmt = function(ctx) {
};


// Visit a parse tree produced by MyGrammerParser#elsestmt.
MyGrammerVisitor.prototype.visitElsestmt = function(ctx) {
};


// Visit a parse tree produced by MyGrammerParser#negationExpr.
MyGrammerVisitor.prototype.visitNegationExpr = function(ctx) {
};


// Visit a parse tree produced by MyGrammerParser#opExpr.
MyGrammerVisitor.prototype.visitOpExpr = function(ctx) {
};


// Visit a parse tree produced by MyGrammerParser#labelLiteral.
MyGrammerVisitor.prototype.visitLabelLiteral = function(ctx) {
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


// Visit a parse tree produced by MyGrammerParser#compareOp.
MyGrammerVisitor.prototype.visitCompareOp = function(ctx) {
};


// Visit a parse tree produced by MyGrammerParser#boolOp.
MyGrammerVisitor.prototype.visitBoolOp = function(ctx) {
};


// Visit a parse tree produced by MyGrammerParser#mathOp.
MyGrammerVisitor.prototype.visitMathOp = function(ctx) {
};


// Visit a parse tree produced by MyGrammerParser#additionOp.
MyGrammerVisitor.prototype.visitAdditionOp = function(ctx) {
};


// Visit a parse tree produced by MyGrammerParser#substractionOp.
MyGrammerVisitor.prototype.visitSubstractionOp = function(ctx) {
};


// Visit a parse tree produced by MyGrammerParser#multiplicationOp.
MyGrammerVisitor.prototype.visitMultiplicationOp = function(ctx) {
};


// Visit a parse tree produced by MyGrammerParser#divisionOp.
MyGrammerVisitor.prototype.visitDivisionOp = function(ctx) {
};


// Visit a parse tree produced by MyGrammerParser#andOp.
MyGrammerVisitor.prototype.visitAndOp = function(ctx) {
};


// Visit a parse tree produced by MyGrammerParser#orOp.
MyGrammerVisitor.prototype.visitOrOp = function(ctx) {
};


// Visit a parse tree produced by MyGrammerParser#noOp.
MyGrammerVisitor.prototype.visitNoOp = function(ctx) {
};


// Visit a parse tree produced by MyGrammerParser#ltOp.
MyGrammerVisitor.prototype.visitLtOp = function(ctx) {
};


// Visit a parse tree produced by MyGrammerParser#ltEqOp.
MyGrammerVisitor.prototype.visitLtEqOp = function(ctx) {
};


// Visit a parse tree produced by MyGrammerParser#gtOp.
MyGrammerVisitor.prototype.visitGtOp = function(ctx) {
};


// Visit a parse tree produced by MyGrammerParser#gtEqOp.
MyGrammerVisitor.prototype.visitGtEqOp = function(ctx) {
};


// Visit a parse tree produced by MyGrammerParser#nEqOp.
MyGrammerVisitor.prototype.visitNEqOp = function(ctx) {
};


// Visit a parse tree produced by MyGrammerParser#eqOp.
MyGrammerVisitor.prototype.visitEqOp = function(ctx) {
};



exports.MyGrammerVisitor = MyGrammerVisitor;