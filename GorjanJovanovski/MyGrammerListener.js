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


// Enter a parse tree produced by MyGrammerParser#block.
MyGrammerListener.prototype.enterBlock = function(ctx) {
};

// Exit a parse tree produced by MyGrammerParser#block.
MyGrammerListener.prototype.exitBlock = function(ctx) {
};


// Enter a parse tree produced by MyGrammerParser#blockstmt.
MyGrammerListener.prototype.enterBlockstmt = function(ctx) {
};

// Exit a parse tree produced by MyGrammerParser#blockstmt.
MyGrammerListener.prototype.exitBlockstmt = function(ctx) {
};


// Enter a parse tree produced by MyGrammerParser#question.
MyGrammerListener.prototype.enterQuestion = function(ctx) {
};

// Exit a parse tree produced by MyGrammerParser#question.
MyGrammerListener.prototype.exitQuestion = function(ctx) {
};


// Enter a parse tree produced by MyGrammerParser#computedquestion.
MyGrammerListener.prototype.enterComputedquestion = function(ctx) {
};

// Exit a parse tree produced by MyGrammerParser#computedquestion.
MyGrammerListener.prototype.exitComputedquestion = function(ctx) {
};


// Enter a parse tree produced by MyGrammerParser#ifstmt.
MyGrammerListener.prototype.enterIfstmt = function(ctx) {
};

// Exit a parse tree produced by MyGrammerParser#ifstmt.
MyGrammerListener.prototype.exitIfstmt = function(ctx) {
};


// Enter a parse tree produced by MyGrammerParser#expr.
MyGrammerListener.prototype.enterExpr = function(ctx) {
};

// Exit a parse tree produced by MyGrammerParser#expr.
MyGrammerListener.prototype.exitExpr = function(ctx) {
};



exports.MyGrammerListener = MyGrammerListener;