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

	var QuestionPrinter = function () {
    	MyGrammerListener.MyGrammerListener.call(this);
    	return this;
	};

	QuestionPrinter.prototype = Object.create(MyGrammerListener.MyGrammerListener.prototype);

	QuestionPrinter.prototype.exitQuestion = function (ctx) {
	    var question = {};
	    var source = ctx.start.source[1].strdata;
	    question.text = source.substring(ctx.children[0].symbol.start, ctx.children[0].symbol.stop+1);
	    question.label = source.substring(ctx.children[1].symbol.start, ctx.children[1].symbol.stop+1);
	    if(ctx.children[3].children.length==1){
	    	//just a terminal node
	    	question.type = source.substring(ctx.children[3].children[0].symbol.start, ctx.children[3].children[0].symbol.stop+1);
	    }
	    else{
	    	//complex expr node
	    	//TODO
	    	question.type = "readOnly";
	    	question.value = 0;
	    }
	    
	    questions.push(question);
	};

	QuestionPrinter.prototype.exitForm = function (ctx) {
	    renderQuestions(questions);
	};

	var printer = new QuestionPrinter();
	antlr4.tree.ParseTreeWalker.DEFAULT.walk(printer, parseTree);
}

function renderQuestions(questions){
	var output = "";

	for(var i=0;i<questions.length;i++){
		output += generateQuestionHTML(questions[i]);
	}

	$("#output").html(output);
}

function generateQuestionHTML(question){
	var html = "<div id='" + question.label + "'>"
		+ question.text + " ";
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
		case "readOnly": html += "<input name='"+ question.label
			+ "' type='text' value='"+ question.value +"' disabled/>";
						break;
		default: html += "<input name='"+ question.label
			+ "' type='text'/>";
						break;
	}
	html += "</div>";
	return html;
}

$("#generate").click(function(){
	var input = $("#input").val().replace(/[\r\t]/g, "").trim();
	init(input);
});

// var input = 'form testForm {\n"Test question number one"\nfirstQuestion: boolean\n"Test question number two"\nsecondQuestion: boolean\n}';
// init(input);
