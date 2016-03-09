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


// Visit a parse tree produced by QLSParser#pageStylesheetStatement.
QLSVisitor.prototype.visitPageStylesheetStatement = function(ctx) {
};


// Visit a parse tree produced by QLSParser#typeDefaultStylesheetStatement.
QLSVisitor.prototype.visitTypeDefaultStylesheetStatement = function(ctx) {
};


// Visit a parse tree produced by QLSParser#pageBlock.
QLSVisitor.prototype.visitPageBlock = function(ctx) {
};


// Visit a parse tree produced by QLSParser#sectionPageStatement.
QLSVisitor.prototype.visitSectionPageStatement = function(ctx) {
};


// Visit a parse tree produced by QLSParser#questionPageStatement.
QLSVisitor.prototype.visitQuestionPageStatement = function(ctx) {
};


// Visit a parse tree produced by QLSParser#typeDefaultPageStatement.
QLSVisitor.prototype.visitTypeDefaultPageStatement = function(ctx) {
};


// Visit a parse tree produced by QLSParser#typeDefaultStatement.
QLSVisitor.prototype.visitTypeDefaultStatement = function(ctx) {
};


// Visit a parse tree produced by QLSParser#styleBlock.
QLSVisitor.prototype.visitStyleBlock = function(ctx) {
};


// Visit a parse tree produced by QLSParser#widgetStyleStatement.
QLSVisitor.prototype.visitWidgetStyleStatement = function(ctx) {
};


// Visit a parse tree produced by QLSParser#argStyleStatement.
QLSVisitor.prototype.visitArgStyleStatement = function(ctx) {
};


// Visit a parse tree produced by QLSParser#sliderWidgetType.
QLSVisitor.prototype.visitSliderWidgetType = function(ctx) {
};


// Visit a parse tree produced by QLSParser#textWidgetType.
QLSVisitor.prototype.visitTextWidgetType = function(ctx) {
};


// Visit a parse tree produced by QLSParser#radioWidgetType.
QLSVisitor.prototype.visitRadioWidgetType = function(ctx) {
};


// Visit a parse tree produced by QLSParser#valueOptionList.
QLSVisitor.prototype.visitValueOptionList = function(ctx) {
};


// Visit a parse tree produced by QLSParser#moreValueOptions.
QLSVisitor.prototype.visitMoreValueOptions = function(ctx) {
};


// Visit a parse tree produced by QLSParser#lastValueOption.
QLSVisitor.prototype.visitLastValueOption = function(ctx) {
};


// Visit a parse tree produced by QLSParser#booleanLiteral.
QLSVisitor.prototype.visitBooleanLiteral = function(ctx) {
};


// Visit a parse tree produced by QLSParser#stringLiteral.
QLSVisitor.prototype.visitStringLiteral = function(ctx) {
};


// Visit a parse tree produced by QLSParser#integerLiteral.
QLSVisitor.prototype.visitIntegerLiteral = function(ctx) {
};


// Visit a parse tree produced by QLSParser#floatLiteral.
QLSVisitor.prototype.visitFloatLiteral = function(ctx) {
};


// Visit a parse tree produced by QLSParser#moneyLiteral.
QLSVisitor.prototype.visitMoneyLiteral = function(ctx) {
};


// Visit a parse tree produced by QLSParser#type.
QLSVisitor.prototype.visitType = function(ctx) {
};



exports.QLSVisitor = QLSVisitor;