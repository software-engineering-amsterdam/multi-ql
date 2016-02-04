"use strict";

var antlr4 = require('antlr4/index');
var MyGrammerLexer = require('MyGrammerLexer');
var MyGrammerParser = require('MyGrammerParser');
var MyGrammerListener = require('MyGrammerListener');
var ast = {};

function init(inputString){
	var characters = new antlr4.InputStream(inputString);
	var lexer = new MyGrammerLexer.MyGrammerLexer(characters);
	var tokens  = new antlr4.CommonTokenStream(lexer);
	var parser = new MyGrammerParser.MyGrammerParser(tokens);
	parser.buildParseTrees = true;
	var tree = parser.form();
	parseQuestions(tree);
}	

function parseQuestions(parseTree){
	
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
	    	question.value = 0;
	    }

	    if(dependencies.length>0){
	    	question.dependencies = dependencies.slice(0);
	    }

	    if(question.type=="number" || question.type=="float" || question.type=="money"){
	    	question.value = 0;
	    }
	    else if(question.type=="boolean"){
	    	question.value = false;
	    }
	    else if(question.type=="string"){
	    	question.value = "";
	    }

	    questions.push(question);
	};

	QuestionPrinter.prototype.exitForm = function (ctx) {
		createAST(questions);
	    renderQuestions(questions);
	    setHandlers();
	    refreshGUI();
	};

	var printer = new QuestionPrinter();
	antlr4.tree.ParseTreeWalker.DEFAULT.walk(printer, parseTree);
}

function setHandlers(){
	$("input").change(function(){
		var label = $(this).attr("name");
		if($(this).attr("type")=="checkbox"){
			setASTQuestionValue(label, $(this).is(":checked"));
		}
		else{
			setASTQuestionValue(label, $(this).val());
		}
		refreshGUI();
	});
}

function createAST(questions){
	ast = {};
	ast.questions = questions;
	ast.labels = new Array();
	for(var i=0;i<questions.length;i++){
		ast.labels.push(questions[i].label);
	}
}

function setASTQuestionValue(label, value){
	for(var i=0;i<ast.questions.length;i++){
		if(ast.questions[i].label==label){
			if(ast.questions[i].type == "integer"){
				ast.questions[i].value = parseInt(value);
			}
			else if(ast.questions[i].type == "float" || ast.questions[i].type == "money"){
				ast.questions[i].value = parseFloat(value);
			}
			else{
				ast.questions[i].value = value;
			}
		}
	}
}

function refreshGUI(){
	for(var i=0;i<ast.questions.length;i++){
		var question = ast.questions[i];
		if(question.dependencies != undefined){
			var dependencies = question.dependencies;
			var shouldShow = true;

			for(var j=0;j<dependencies.length;j++){
				if(!evaluate(dependencies[j])){
					shouldShow = false;
					break;
				}
			}

			if(shouldShow){
				$("div[label='"+question.label+"']").show();
				
			}
			else{
				$("div[label='"+question.label+"']").hide();
			}
		}
		if(question.expr!=undefined){
			ast.questions[i].value = evaluate(question.expr);
			$("input[name='"+question.label+"']").val(ast.questions[i].value);
		}
	}
}

function evaluate(statement){
	var evalStmt = "";
	for(var i=0;i<ast.labels.length;i++){
		if(getQuestionType(ast.labels[i]) == "string"){
			evalStmt += "var " + ast.labels[i] + " = '" + getQuestionValue(ast.labels[i]) + "';";
		}
		else{
			evalStmt += "var " + ast.labels[i] + " = " + getQuestionValue(ast.labels[i]) + ";";
		}
	}
	
	return eval(evalStmt + statement + ";");
}

function getQuestionValue(label){
	for(var i=0;i<ast.questions.length;i++){
		var question = ast.questions[i];
		if(question.label == label){
			return question.value;
		}
	}
}

function getQuestionType(label){
	for(var i=0;i<ast.questions.length;i++){
		var question = ast.questions[i];
		if(question.label == label){
			return question.type;
		}
	}
}

function generateQuestionHTML(question){
	var html = "<div label='" + question.label + "'> ["
		+ question.label + " - " + question.type + "] " + question.text + " ";
	switch(question.type){
		case "integer": html += "<input name='"+ question.label
			+ "' type='number'";
						break;
		case "money": html += "<input name='"+ question.label
			+ "' type='number'";
						break;
		case "float": html += "<input name='"+ question.label
			+ "' type='number'";
						break;
		case "string": html += "<input name='"+ question.label
			+ "' type='text'";
						break;
		case "boolean": html += "<input name='"+ question.label
			+ "' type='checkbox'";
						break;
		default: html += "<input name='"+ question.label
			+ "' type='text'";
						break;
	}

	if(question.expr != undefined){
		html += " disabled";
	}

	html += "/>";
	
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