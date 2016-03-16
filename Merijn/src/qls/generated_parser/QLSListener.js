// Generated from C:/Users/mwijngaard/Documents/Projects/multi-ql/Merijn/src/qls\QLS.g4 by ANTLR 4.5.1
// jshint ignore: start
var antlr4 = require('antlr4/index');

// This class defines a complete listener for a parse tree produced by QLSParser.
function QLSListener() {
	antlr4.tree.ParseTreeListener.call(this);
	return this;
}

QLSListener.prototype = Object.create(antlr4.tree.ParseTreeListener.prototype);
QLSListener.prototype.constructor = QLSListener;

// Enter a parse tree produced by QLSParser#stylesheet.
QLSListener.prototype.enterStylesheet = function(ctx) {
};

// Exit a parse tree produced by QLSParser#stylesheet.
QLSListener.prototype.exitStylesheet = function(ctx) {
};


// Enter a parse tree produced by QLSParser#stylesheetBlock.
QLSListener.prototype.enterStylesheetBlock = function(ctx) {
};

// Exit a parse tree produced by QLSParser#stylesheetBlock.
QLSListener.prototype.exitStylesheetBlock = function(ctx) {
};


// Enter a parse tree produced by QLSParser#pageStylesheetStatement.
QLSListener.prototype.enterPageStylesheetStatement = function(ctx) {
};

// Exit a parse tree produced by QLSParser#pageStylesheetStatement.
QLSListener.prototype.exitPageStylesheetStatement = function(ctx) {
};


// Enter a parse tree produced by QLSParser#typeDefaultStylesheetStatement.
QLSListener.prototype.enterTypeDefaultStylesheetStatement = function(ctx) {
};

// Exit a parse tree produced by QLSParser#typeDefaultStylesheetStatement.
QLSListener.prototype.exitTypeDefaultStylesheetStatement = function(ctx) {
};


// Enter a parse tree produced by QLSParser#pageBlock.
QLSListener.prototype.enterPageBlock = function(ctx) {
};

// Exit a parse tree produced by QLSParser#pageBlock.
QLSListener.prototype.exitPageBlock = function(ctx) {
};


// Enter a parse tree produced by QLSParser#sectionPageStatement.
QLSListener.prototype.enterSectionPageStatement = function(ctx) {
};

// Exit a parse tree produced by QLSParser#sectionPageStatement.
QLSListener.prototype.exitSectionPageStatement = function(ctx) {
};


// Enter a parse tree produced by QLSParser#questionPageStatement.
QLSListener.prototype.enterQuestionPageStatement = function(ctx) {
};

// Exit a parse tree produced by QLSParser#questionPageStatement.
QLSListener.prototype.exitQuestionPageStatement = function(ctx) {
};


// Enter a parse tree produced by QLSParser#configuredQuestionPageStatement.
QLSListener.prototype.enterConfiguredQuestionPageStatement = function(ctx) {
};

// Exit a parse tree produced by QLSParser#configuredQuestionPageStatement.
QLSListener.prototype.exitConfiguredQuestionPageStatement = function(ctx) {
};


// Enter a parse tree produced by QLSParser#typeDefaultPageStatement.
QLSListener.prototype.enterTypeDefaultPageStatement = function(ctx) {
};

// Exit a parse tree produced by QLSParser#typeDefaultPageStatement.
QLSListener.prototype.exitTypeDefaultPageStatement = function(ctx) {
};


// Enter a parse tree produced by QLSParser#typeDefaultStatement.
QLSListener.prototype.enterTypeDefaultStatement = function(ctx) {
};

// Exit a parse tree produced by QLSParser#typeDefaultStatement.
QLSListener.prototype.exitTypeDefaultStatement = function(ctx) {
};


// Enter a parse tree produced by QLSParser#widgetConfiguration.
QLSListener.prototype.enterWidgetConfiguration = function(ctx) {
};

// Exit a parse tree produced by QLSParser#widgetConfiguration.
QLSListener.prototype.exitWidgetConfiguration = function(ctx) {
};


// Enter a parse tree produced by QLSParser#widgetArg.
QLSListener.prototype.enterWidgetArg = function(ctx) {
};

// Exit a parse tree produced by QLSParser#widgetArg.
QLSListener.prototype.exitWidgetArg = function(ctx) {
};


// Enter a parse tree produced by QLSParser#sliderWidgetType.
QLSListener.prototype.enterSliderWidgetType = function(ctx) {
};

// Exit a parse tree produced by QLSParser#sliderWidgetType.
QLSListener.prototype.exitSliderWidgetType = function(ctx) {
};


// Enter a parse tree produced by QLSParser#textWidgetType.
QLSListener.prototype.enterTextWidgetType = function(ctx) {
};

// Exit a parse tree produced by QLSParser#textWidgetType.
QLSListener.prototype.exitTextWidgetType = function(ctx) {
};


// Enter a parse tree produced by QLSParser#radioWidgetType.
QLSListener.prototype.enterRadioWidgetType = function(ctx) {
};

// Exit a parse tree produced by QLSParser#radioWidgetType.
QLSListener.prototype.exitRadioWidgetType = function(ctx) {
};


// Enter a parse tree produced by QLSParser#valueOptionList.
QLSListener.prototype.enterValueOptionList = function(ctx) {
};

// Exit a parse tree produced by QLSParser#valueOptionList.
QLSListener.prototype.exitValueOptionList = function(ctx) {
};


// Enter a parse tree produced by QLSParser#moreValueOptions.
QLSListener.prototype.enterMoreValueOptions = function(ctx) {
};

// Exit a parse tree produced by QLSParser#moreValueOptions.
QLSListener.prototype.exitMoreValueOptions = function(ctx) {
};


// Enter a parse tree produced by QLSParser#lastValueOption.
QLSListener.prototype.enterLastValueOption = function(ctx) {
};

// Exit a parse tree produced by QLSParser#lastValueOption.
QLSListener.prototype.exitLastValueOption = function(ctx) {
};


// Enter a parse tree produced by QLSParser#booleanLiteral.
QLSListener.prototype.enterBooleanLiteral = function(ctx) {
};

// Exit a parse tree produced by QLSParser#booleanLiteral.
QLSListener.prototype.exitBooleanLiteral = function(ctx) {
};


// Enter a parse tree produced by QLSParser#stringLiteral.
QLSListener.prototype.enterStringLiteral = function(ctx) {
};

// Exit a parse tree produced by QLSParser#stringLiteral.
QLSListener.prototype.exitStringLiteral = function(ctx) {
};


// Enter a parse tree produced by QLSParser#integerLiteral.
QLSListener.prototype.enterIntegerLiteral = function(ctx) {
};

// Exit a parse tree produced by QLSParser#integerLiteral.
QLSListener.prototype.exitIntegerLiteral = function(ctx) {
};


// Enter a parse tree produced by QLSParser#floatLiteral.
QLSListener.prototype.enterFloatLiteral = function(ctx) {
};

// Exit a parse tree produced by QLSParser#floatLiteral.
QLSListener.prototype.exitFloatLiteral = function(ctx) {
};


// Enter a parse tree produced by QLSParser#moneyLiteral.
QLSListener.prototype.enterMoneyLiteral = function(ctx) {
};

// Exit a parse tree produced by QLSParser#moneyLiteral.
QLSListener.prototype.exitMoneyLiteral = function(ctx) {
};


// Enter a parse tree produced by QLSParser#type.
QLSListener.prototype.enterType = function(ctx) {
};

// Exit a parse tree produced by QLSParser#type.
QLSListener.prototype.exitType = function(ctx) {
};



exports.QLSListener = QLSListener;