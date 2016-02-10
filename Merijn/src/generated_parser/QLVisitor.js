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


// Visit a parse tree produced by QLParser#statement.
QLVisitor.prototype.visitStatement = function(ctx) {
};


// Visit a parse tree produced by QLParser#ifStatement.
QLVisitor.prototype.visitIfStatement = function(ctx) {
};


// Visit a parse tree produced by QLParser#question.
QLVisitor.prototype.visitQuestion = function(ctx) {
};


// Visit a parse tree produced by QLParser#expr.
QLVisitor.prototype.visitExpr = function(ctx) {
};


// Visit a parse tree produced by QLParser#literal.
QLVisitor.prototype.visitLiteral = function(ctx) {
};


// Visit a parse tree produced by QLParser#type.
QLVisitor.prototype.visitType = function(ctx) {
};



exports.QLVisitor = QLVisitor;