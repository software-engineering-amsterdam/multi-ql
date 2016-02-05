// Generated from QL.g4 by ANTLR 4.5.2
// jshint ignore: start
var antlr4 = require('antlr4/index');

// This class defines a complete listener for a parse tree produced by QLParser.
function QLListener() {
	antlr4.tree.ParseTreeListener.call(this);
	return this;
}

QLListener.prototype = Object.create(antlr4.tree.ParseTreeListener.prototype);
QLListener.prototype.constructor = QLListener;

// Enter a parse tree produced by QLParser#form.
QLListener.prototype.enterForm = function(ctx) {
};

// Exit a parse tree produced by QLParser#form.
QLListener.prototype.exitForm = function(ctx) {
};


// Enter a parse tree produced by QLParser#question.
QLListener.prototype.enterQuestion = function(ctx) {
};

// Exit a parse tree produced by QLParser#question.
QLListener.prototype.exitQuestion = function(ctx) {
};



exports.QLListener = QLListener;