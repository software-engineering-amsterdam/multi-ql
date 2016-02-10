// Generated from MyGrammer.g4 by ANTLR 4.5.2
// jshint ignore: start
var antlr4 = require('antlr4/index');

// This class defines a complete listener for a parse tree produced by MyGrammerParser.
function MyGrammerListener() {
	antlr4.tree.ParseTreeListener.call(this);
	return this;
}

MyGrammerListener.prototype = Object.create(antlr4.tree.ParseTreeListener.prototype);
MyGrammerListener.prototype.constructor = MyGrammerListener;

// Enter a parse tree produced by MyGrammerParser#form.
MyGrammerListener.prototype.enterForm = function(ctx) {
};

// Exit a parse tree produced by MyGrammerParser#form.
MyGrammerListener.prototype.exitForm = function(ctx) {
};


// Enter a parse tree produced by MyGrammerParser#queries.
MyGrammerListener.prototype.enterQueries = function(ctx) {
};

// Exit a parse tree produced by MyGrammerParser#queries.
MyGrammerListener.prototype.exitQueries = function(ctx) {
};


// Enter a parse tree produced by MyGrammerParser#question.
MyGrammerListener.prototype.enterQuestion = function(ctx) {
};

// Exit a parse tree produced by MyGrammerParser#question.
MyGrammerListener.prototype.exitQuestion = function(ctx) {
};


// Enter a parse tree produced by MyGrammerParser#questionText.
MyGrammerListener.prototype.enterQuestionText = function(ctx) {
};

// Exit a parse tree produced by MyGrammerParser#questionText.
MyGrammerListener.prototype.exitQuestionText = function(ctx) {
};


// Enter a parse tree produced by MyGrammerParser#questionLabel.
MyGrammerListener.prototype.enterQuestionLabel = function(ctx) {
};

// Exit a parse tree produced by MyGrammerParser#questionLabel.
MyGrammerListener.prototype.exitQuestionLabel = function(ctx) {
};


// Enter a parse tree produced by MyGrammerParser#questionValue.
MyGrammerListener.prototype.enterQuestionValue = function(ctx) {
};

// Exit a parse tree produced by MyGrammerParser#questionValue.
MyGrammerListener.prototype.exitQuestionValue = function(ctx) {
};


// Enter a parse tree produced by MyGrammerParser#ifstmt.
MyGrammerListener.prototype.enterIfstmt = function(ctx) {
};

// Exit a parse tree produced by MyGrammerParser#ifstmt.
MyGrammerListener.prototype.exitIfstmt = function(ctx) {
};


// Enter a parse tree produced by MyGrammerParser#elsestmt.
MyGrammerListener.prototype.enterElsestmt = function(ctx) {
};

// Exit a parse tree produced by MyGrammerParser#elsestmt.
MyGrammerListener.prototype.exitElsestmt = function(ctx) {
};


// Enter a parse tree produced by MyGrammerParser#negationExpr.
MyGrammerListener.prototype.enterNegationExpr = function(ctx) {
};

// Exit a parse tree produced by MyGrammerParser#negationExpr.
MyGrammerListener.prototype.exitNegationExpr = function(ctx) {
};


// Enter a parse tree produced by MyGrammerParser#opExpr.
MyGrammerListener.prototype.enterOpExpr = function(ctx) {
};

// Exit a parse tree produced by MyGrammerParser#opExpr.
MyGrammerListener.prototype.exitOpExpr = function(ctx) {
};


// Enter a parse tree produced by MyGrammerParser#labelLiteral.
MyGrammerListener.prototype.enterLabelLiteral = function(ctx) {
};

// Exit a parse tree produced by MyGrammerParser#labelLiteral.
MyGrammerListener.prototype.exitLabelLiteral = function(ctx) {
};


// Enter a parse tree produced by MyGrammerParser#booleanLiteral.
MyGrammerListener.prototype.enterBooleanLiteral = function(ctx) {
};

// Exit a parse tree produced by MyGrammerParser#booleanLiteral.
MyGrammerListener.prototype.exitBooleanLiteral = function(ctx) {
};


// Enter a parse tree produced by MyGrammerParser#parenthesisExpr.
MyGrammerListener.prototype.enterParenthesisExpr = function(ctx) {
};

// Exit a parse tree produced by MyGrammerParser#parenthesisExpr.
MyGrammerListener.prototype.exitParenthesisExpr = function(ctx) {
};


// Enter a parse tree produced by MyGrammerParser#numberLiteral.
MyGrammerListener.prototype.enterNumberLiteral = function(ctx) {
};

// Exit a parse tree produced by MyGrammerParser#numberLiteral.
MyGrammerListener.prototype.exitNumberLiteral = function(ctx) {
};


// Enter a parse tree produced by MyGrammerParser#compareOp.
MyGrammerListener.prototype.enterCompareOp = function(ctx) {
};

// Exit a parse tree produced by MyGrammerParser#compareOp.
MyGrammerListener.prototype.exitCompareOp = function(ctx) {
};


// Enter a parse tree produced by MyGrammerParser#boolOp.
MyGrammerListener.prototype.enterBoolOp = function(ctx) {
};

// Exit a parse tree produced by MyGrammerParser#boolOp.
MyGrammerListener.prototype.exitBoolOp = function(ctx) {
};


// Enter a parse tree produced by MyGrammerParser#mathOp.
MyGrammerListener.prototype.enterMathOp = function(ctx) {
};

// Exit a parse tree produced by MyGrammerParser#mathOp.
MyGrammerListener.prototype.exitMathOp = function(ctx) {
};


// Enter a parse tree produced by MyGrammerParser#additionOp.
MyGrammerListener.prototype.enterAdditionOp = function(ctx) {
};

// Exit a parse tree produced by MyGrammerParser#additionOp.
MyGrammerListener.prototype.exitAdditionOp = function(ctx) {
};


// Enter a parse tree produced by MyGrammerParser#substractionOp.
MyGrammerListener.prototype.enterSubstractionOp = function(ctx) {
};

// Exit a parse tree produced by MyGrammerParser#substractionOp.
MyGrammerListener.prototype.exitSubstractionOp = function(ctx) {
};


// Enter a parse tree produced by MyGrammerParser#multiplicationOp.
MyGrammerListener.prototype.enterMultiplicationOp = function(ctx) {
};

// Exit a parse tree produced by MyGrammerParser#multiplicationOp.
MyGrammerListener.prototype.exitMultiplicationOp = function(ctx) {
};


// Enter a parse tree produced by MyGrammerParser#divisionOp.
MyGrammerListener.prototype.enterDivisionOp = function(ctx) {
};

// Exit a parse tree produced by MyGrammerParser#divisionOp.
MyGrammerListener.prototype.exitDivisionOp = function(ctx) {
};


// Enter a parse tree produced by MyGrammerParser#andOp.
MyGrammerListener.prototype.enterAndOp = function(ctx) {
};

// Exit a parse tree produced by MyGrammerParser#andOp.
MyGrammerListener.prototype.exitAndOp = function(ctx) {
};


// Enter a parse tree produced by MyGrammerParser#orOp.
MyGrammerListener.prototype.enterOrOp = function(ctx) {
};

// Exit a parse tree produced by MyGrammerParser#orOp.
MyGrammerListener.prototype.exitOrOp = function(ctx) {
};


// Enter a parse tree produced by MyGrammerParser#noOp.
MyGrammerListener.prototype.enterNoOp = function(ctx) {
};

// Exit a parse tree produced by MyGrammerParser#noOp.
MyGrammerListener.prototype.exitNoOp = function(ctx) {
};


// Enter a parse tree produced by MyGrammerParser#ltOp.
MyGrammerListener.prototype.enterLtOp = function(ctx) {
};

// Exit a parse tree produced by MyGrammerParser#ltOp.
MyGrammerListener.prototype.exitLtOp = function(ctx) {
};


// Enter a parse tree produced by MyGrammerParser#ltEqOp.
MyGrammerListener.prototype.enterLtEqOp = function(ctx) {
};

// Exit a parse tree produced by MyGrammerParser#ltEqOp.
MyGrammerListener.prototype.exitLtEqOp = function(ctx) {
};


// Enter a parse tree produced by MyGrammerParser#gtOp.
MyGrammerListener.prototype.enterGtOp = function(ctx) {
};

// Exit a parse tree produced by MyGrammerParser#gtOp.
MyGrammerListener.prototype.exitGtOp = function(ctx) {
};


// Enter a parse tree produced by MyGrammerParser#gtEqOp.
MyGrammerListener.prototype.enterGtEqOp = function(ctx) {
};

// Exit a parse tree produced by MyGrammerParser#gtEqOp.
MyGrammerListener.prototype.exitGtEqOp = function(ctx) {
};


// Enter a parse tree produced by MyGrammerParser#nEqOp.
MyGrammerListener.prototype.enterNEqOp = function(ctx) {
};

// Exit a parse tree produced by MyGrammerParser#nEqOp.
MyGrammerListener.prototype.exitNEqOp = function(ctx) {
};


// Enter a parse tree produced by MyGrammerParser#eqOp.
MyGrammerListener.prototype.enterEqOp = function(ctx) {
};

// Exit a parse tree produced by MyGrammerParser#eqOp.
MyGrammerListener.prototype.exitEqOp = function(ctx) {
};



exports.MyGrammerListener = MyGrammerListener;