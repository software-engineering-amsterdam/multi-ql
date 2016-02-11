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


// Enter a parse tree produced by MyGrammerParser#ifstmt.
MyGrammerListener.prototype.enterIfstmt = function(ctx) {
};

// Exit a parse tree produced by MyGrammerParser#ifstmt.
MyGrammerListener.prototype.exitIfstmt = function(ctx) {
};


// Enter a parse tree produced by MyGrammerParser#decimalLiteral.
MyGrammerListener.prototype.enterDecimalLiteral = function(ctx) {
};

// Exit a parse tree produced by MyGrammerParser#decimalLiteral.
MyGrammerListener.prototype.exitDecimalLiteral = function(ctx) {
};


// Enter a parse tree produced by MyGrammerParser#labelNode.
MyGrammerListener.prototype.enterLabelNode = function(ctx) {
};

// Exit a parse tree produced by MyGrammerParser#labelNode.
MyGrammerListener.prototype.exitLabelNode = function(ctx) {
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



exports.MyGrammerListener = MyGrammerListener;