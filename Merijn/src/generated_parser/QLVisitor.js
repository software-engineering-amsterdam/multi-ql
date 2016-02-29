// Generated from C:/Users/mwijngaard/Documents/Projects/multi-ql/Merijn/src/generated_parser\QL.g4 by ANTLR 4.5.1
// jshint ignore: start
var antlr4 = require('antlr4/index');

// This class defines a complete generic visitor for a parse tree produced by QLParser.

function QLVisitor() {
	antlr4.tree.ParseTreeVisitor.call(this);
	return this;
}

QLVisitor.prototype = Object.create(antlr4.tree.ParseTreeVisitor.prototype);
QLVisitor.prototype.constructor = QLVisitor;

// Visit a parse tree produced by QLParser#form.
QLVisitor.prototype.visitForm = function(ctx) {
};


// Visit a parse tree produced by QLParser#block.
QLVisitor.prototype.visitBlock = function(ctx) {
};


// Visit a parse tree produced by QLParser#ifStatementCase.
QLVisitor.prototype.visitIfStatementCase = function(ctx) {
};


// Visit a parse tree produced by QLParser#questionStatementCase.
QLVisitor.prototype.visitQuestionStatementCase = function(ctx) {
};


// Visit a parse tree produced by QLParser#if_.
QLVisitor.prototype.visitIf_ = function(ctx) {
};


// Visit a parse tree produced by QLParser#questionCase.
QLVisitor.prototype.visitQuestionCase = function(ctx) {
};


// Visit a parse tree produced by QLParser#exprQuestionCase.
QLVisitor.prototype.visitExprQuestionCase = function(ctx) {
};


// Visit a parse tree produced by QLParser#unaryPrefixExprCase.
QLVisitor.prototype.visitUnaryPrefixExprCase = function(ctx) {
};


// Visit a parse tree produced by QLParser#parenExprCase.
QLVisitor.prototype.visitParenExprCase = function(ctx) {
};


// Visit a parse tree produced by QLParser#infixExprCase.
QLVisitor.prototype.visitInfixExprCase = function(ctx) {
};


// Visit a parse tree produced by QLParser#identifierExprCase.
QLVisitor.prototype.visitIdentifierExprCase = function(ctx) {
};


// Visit a parse tree produced by QLParser#literalExprCase.
QLVisitor.prototype.visitLiteralExprCase = function(ctx) {
};


// Visit a parse tree produced by QLParser#booleanLiteralCase.
QLVisitor.prototype.visitBooleanLiteralCase = function(ctx) {
};


// Visit a parse tree produced by QLParser#stringLiteralCase.
QLVisitor.prototype.visitStringLiteralCase = function(ctx) {
};


// Visit a parse tree produced by QLParser#integerLiteralCase.
QLVisitor.prototype.visitIntegerLiteralCase = function(ctx) {
};


// Visit a parse tree produced by QLParser#floatLiteralCase.
QLVisitor.prototype.visitFloatLiteralCase = function(ctx) {
};


// Visit a parse tree produced by QLParser#moneyLiteralCase.
QLVisitor.prototype.visitMoneyLiteralCase = function(ctx) {
};


// Visit a parse tree produced by QLParser#type.
QLVisitor.prototype.visitType = function(ctx) {
};



exports.QLVisitor = QLVisitor;