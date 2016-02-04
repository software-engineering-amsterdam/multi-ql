"use strict";

var antlr4 = require('antlr4/index');
var MyGrammerLexer = require('MyGrammerLexer');
var MyGrammerParser = require('MyGrammerParser');
var MyGrammerListener = require('MyGrammerListener');
var ast = {};
var errors;
var warnings;

function initiate(inputString){

	var characters = new antlr4.InputStream(inputString);
	var lexer = new MyGrammerLexer.MyGrammerLexer(characters);
	var tokens  = new antlr4.CommonTokenStream(lexer);
	var parser = new MyGrammerParser.MyGrammerParser(tokens);
	
	warnings = new Set();
	errors = new Set();
	$("#errorpanel").hide();
	$("#warningpanel").hide();
	$("#formWrapper").show();

	var ErrorListener = function() {
		antlr4.error.ErrorListener.call(this);
	  	return this;
	};

	ErrorListener.prototype = Object.create(antlr4.error.ErrorListener.prototype);
	ErrorListener.prototype.constructor = ErrorListener;
	ErrorListener.prototype.syntaxError = function(rec, sym, line, col, msg, e) {
	  	errors.add("GRAMMAR ERR: " + line + ":" + col + " - " + msg);
	  	fillPanel("error", errors, true);
	};


	lexer.removeErrorListeners();
	parser.removeErrorListeners();
	parser.addErrorListener(new ErrorListener());

	parser.buildParseTrees = true;

	var tree = parser.form();
	parseQuestions(tree);
}	

function parseQuestions(parseTree){
	
	var questions = new Array();
	var dependencies = new Array();

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
	    question.text = question.text.substring(1, question.text.length-1);

	    question.label = questionNode.children[1].getText();

	    if(questionNode.children[3].children.length==1){
	    	//just a terminal node
	    	question.type = questionNode.children[3].getText();

		    if(question.type=="integer" || question.type=="decimal" || question.type=="money" || question.type=="currency"){
		    	question.value = 0;
		    }
		    else if(question.type=="boolean"){
		    	question.value = false;
		    }
		    else if(question.type=="string" || question.type=="date"){
		    	question.value = "";
		    }

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

	    question.visible = true;

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

function createAST(questions){
	ast = {};
	ast.questions = questions;
	ast.labels = new Array();
	ast.texts = new Array();
	for(var i=0;i<questions.length;i++){
		if(ast.labels.indexOf(questions[i].label)>-1){
			errors.add("ERROR: " + "Label '" + questions[i].label + "' is already defined");
			fillPanel("error", errors, true);
		}
		else{
			if(ast.texts.indexOf(questions[i].text)>-1){
				warnings.add("WARNING: " + "Text '" + questions[i].text + "' is already defined");
				fillPanel("warning", warnings);
			}

			ast.labels.push(questions[i].label);
			ast.texts.push(questions[i].text);
		}
	}
}

function setASTQuestionValue(label, value){
	for(var i=0;i<ast.questions.length;i++){
		if(ast.questions[i].label==label){
			if(ast.questions[i].type == "integer"){
				ast.questions[i].value = parseInt(value);
			}
			else if(ast.questions[i].type == "decimal" || ast.questions[i].type == "money" || ast.questions[i].type == "currency"){
				ast.questions[i].value = parseFloat(value);
			}
			else{
				ast.questions[i].value = value;
			}
		}
	}
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


function refreshGUI(){
	for(var i=0;i<ast.questions.length;i++){
		var question = ast.questions[i];
		if(question.dependencies != undefined){
			var dependencies = question.dependencies;
			var shouldShow = true;

			for(var j=0;j<dependencies.length;j++){
				var evalResult = evaluate(dependencies[j]);
				if(typeof evalResult !== "boolean"){
	  				errors.add("ERROR: Condition '"+dependencies[j]+"' is not boolean");
	  				fillPanel("error", errors, true);
				}
				else{
					shouldShow = evalResult;
					if(!shouldShow){
						break;
					}
				}
			}

			if(shouldShow){
				$("div[label='"+question.label+"']").show();
				ast.questions[i].visible = true;
				
			}
			else{
				$("div[label='"+question.label+"']").hide();
				ast.questions[i].visible = false;
			}
		}
		if(question.expr!=undefined){
			ast.questions[i].value = evaluate(question.expr);
			$("input[name='"+question.label+"']").val(ast.questions[i].value);
		}
	}
}

//TODO strings
function evaluate(statement){
	var evalStmt = "";
	ast.labels = ast.labels.sort();
	ast.labels = ast.labels.reverse();
	for(var i=0;i<ast.labels.length;i++){
		var regexObj = new RegExp(ast.labels[i],"g");
		statement = statement.replace(regexObj, getQuestionValue(ast.labels[i]));
	}
	return parser.parse(statement);
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
	var html = "<div class='questionDiv' label='" + question.label + "'>";
	html += "<label class='question'>" + question.text + " ";
    html += "<input name='"+ question.label + "' type='";

	switch(question.type){
		case "integer": 	html += "number";
							break;
		case "decimal": 	html += "number";
							break;
		case "money": 		html += "number";
							break;
		case "currency": 		html += "number";
							break;
		case "string": 		html += "text";
							break;
		case "date": 		html += "text";
							break;
		case "boolean": 	html += "checkbox";
							break;
		default: 			html += "text";
							break;
	}

	html += "'";

	if(question.expr != undefined){
		html += " disabled";
	}

	html += "/></label></div>";

	return html;
}

function renderQuestions(questions){
	var output = "";

	for(var i=0;i<questions.length;i++){
		output += generateQuestionHTML(questions[i]);
	}

	$("#output").html(output);
}

function fillPanel(panel, set, critical){
	var html = "<ul>";

	set.forEach(function(value) {
	  html += "<li>" + value + "</li>";
	});

	html += "</ul>";

	$("#"+panel).html(html);
	$("#"+panel+"panel").show();
	if(critical){
		$("#formWrapper").hide();
	}
}

$("#generate").click(function(){
	initiate($("#input").val());
});

$("#save").click(function(){
	var answers = new Array();
	for(var i=0;i<ast.questions.length;i++){
		var question = ast.questions[i];
		if(question.visible){
			var answer = {};
			answer.questionLabel = question.label;
			answer.questionText = question.text;
			answer.questionType = question.type;
			answer.value = question.value;
			answers.push(answer);
		}
	}
	var blob = new Blob([JSON.stringify(answers, null, 2)], {type: "text/plain;charset=utf-8"});
	saveAs(blob, "answers.txt");
	
});

//Start
initiate($("#input").val());