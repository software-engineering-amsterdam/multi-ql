"use strict";

var antlr4 = require('antlr4/index');
var MyGrammerLexer = require('MyGrammerLexer');
var MyGrammerParser = require('MyGrammerParser');
var MyGrammerListener = require('MyGrammerListener');

function init(inputString){
	var characters = new antlr4.InputStream(inputString);
	var lexer = new MyGrammerLexer.MyGrammerLexer(characters);
	var tokens  = new antlr4.CommonTokenStream(lexer);
	var parser = new MyGrammerParser.MyGrammerParser(tokens);
	parser.buildParseTrees = true;
	var tree = parser.form();
	getQuestions(tree);
}	

function getQuestions(parseTree){
	
	var questions = new Array();
	var dependencies = new Array();

	var questionID = 0;

	var QuestionPrinter = function () {
    	MyGrammerListener.MyGrammerListener.call(this);
    	return this;
	};

	QuestionPrinter.prototype = Object.create(MyGrammerListener.MyGrammerListener.prototype);
	
	QuestionPrinter.prototype.enterIfstmt = function (ifNode) {
		dependencies.push(ifNode.children[1].getText());
	};

	QuestionPrinter.prototype.exitIfstmt = function (ifNode) {
		dependencies.pop();
	};

	QuestionPrinter.prototype.enterElsestmt = function(ctx) {
		dependencies.push("!" + dependencies.pop());
	};

	QuestionPrinter.prototype.exitQuestion = function (questionNode) {
	    var question = {};

	    question.text = questionNode.children[0].getText();
	    question.label = questionNode.children[1].getText();

	    if(questionNode.children[3].children.length==1){
	    	//just a terminal node
	    	question.type = questionNode.children[3].getText();
	    }
	    else{
	    	//complex expr node
	    	question.type = questionNode.children[3].children[0].getText();
	    	question.expr = questionNode.children[3].children[2].getText();
	    }

	    if(dependencies.length>0){
	    	question.dependencies = dependencies.slice(0);
	    }

	    questions.push(question);
	};

	QuestionPrinter.prototype.exitForm = function (ctx) {
	    renderQuestions(questions);
	};

	var printer = new QuestionPrinter();
	antlr4.tree.ParseTreeWalker.DEFAULT.walk(printer, parseTree);
}

function generateQuestionHTML(question){
	var html = "<div id='" + question.label + "'> ["
		+ question.label + " - " + question.type + "] " + question.text + " ";
	switch(question.type){
		case "integer": html += "<input name='"+ question.label
			+ "' type='number'/>";
						break;
		case "money": html += "<input name='"+ question.label
			+ "' type='number'/>";
						break;
		case "float": html += "<input name='"+ question.label
			+ "' type='number'/>";
						break;
		case "string": html += "<input name='"+ question.label
			+ "' type='text'/>";
						break;
		case "boolean": html += "<input name='"+ question.label
			+ "' type='checkbox'/>";
						break;
		default: html += "<input name='"+ question.label
			+ "' type='text'/>";
						break;
	}

	if(question.dependencies != undefined){
		html += " Depends on: " + question.dependencies;
	}

	if(question.expr != undefined){
		html += " Calculated with: " + question.expr;
	}

	html += "</div>";
	return html;
}

function renderQuestions(questions){
	var output = "";

	for(var i=0;i<questions.length;i++){
		output += generateQuestionHTML(questions[i]);
	}

	$("#output").html(output);
}

$("#generate").click(function(){
	var input = $("#input").val().replace(/[\r\t]/g, "").trim();
	init(input);
});