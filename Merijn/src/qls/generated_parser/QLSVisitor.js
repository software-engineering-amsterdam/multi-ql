// Generated from C:/Users/mwijngaard/Documents/Projects/multi-ql/Merijn/src/qls\QLS.g4 by ANTLR 4.5.1
// jshint ignore: start
var antlr4 = require('antlr4/index');

// This class defines a complete generic visitor for a parse tree produced by QLSParser.

function QLSVisitor() {
	antlr4.tree.ParseTreeVisitor.call(this);
	return this;
}

QLSVisitor.prototype = Object.create(antlr4.tree.ParseTreeVisitor.prototype);
QLSVisitor.prototype.constructor = QLSVisitor;

// Visit a parse tree produced by QLSParser#stylesheet.
QLSVisitor.prototype.visitStylesheet = function(ctx) {
};


// Visit a parse tree produced by QLSParser#stylesheetBlock.
QLSVisitor.prototype.visitStylesheetBlock = function(ctx) {
};


// Visit a parse tree produced by QLSParser#stylesheetStatement.
QLSVisitor.prototype.visitStylesheetStatement = function(ctx) {
};


// Visit a parse tree produced by QLSParser#pageBlock.
QLSVisitor.prototype.visitPageBlock = function(ctx) {
};


// Visit a parse tree produced by QLSParser#pageStatement.
QLSVisitor.prototype.visitPageStatement = function(ctx) {
};


// Visit a parse tree produced by QLSParser#defaultWidgetStatement.
QLSVisitor.prototype.visitDefaultWidgetStatement = function(ctx) {
};


// Visit a parse tree produced by QLSParser#widgetBlock.
QLSVisitor.prototype.visitWidgetBlock = function(ctx) {
};


// Visit a parse tree produced by QLSParser#widgetStatement.
QLSVisitor.prototype.visitWidgetStatement = function(ctx) {
};


// Visit a parse tree produced by QLSParser#type.
QLSVisitor.prototype.visitType = function(ctx) {
};


// Visit a parse tree produced by QLSParser#widgetType.
QLSVisitor.prototype.visitWidgetType = function(ctx) {
};


// Visit a parse tree produced by QLSParser#valueOptions.
QLSVisitor.prototype.visitValueOptions = function(ctx) {
};


// Visit a parse tree produced by QLSParser#valueOption.
QLSVisitor.prototype.visitValueOption = function(ctx) {
};



exports.QLSVisitor = QLSVisitor;