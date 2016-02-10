"use strict";

var antlr4 = require('antlr4/index');
var MyGrammerLexer = require('MyGrammerLexer');
var MyGrammerParser = require('MyGrammerParser');
var MyGrammerListener = require('MyGrammerListener');
var MyGrammerVisitor = require('MyGrammerVisitor');
var ast;
var newAst;
var errors;
var warnings;

var JSExpr = function (expr){
	this.expr = expr;
};

var JSOpExpr = function (left, op, right){
	this.left = left;
	this.op = op;
	this.right = right;
}

var JSNoOpExpr = function (expr){
	this.expr = expr;
}

function initiate(inputString){

	var characters = new antlr4.InputStream(inputString);
	var lexer = new MyGrammerLexer.MyGrammerLexer(characters);
	var tokens  = new antlr4.CommonTokenStream(lexer);
	var parserANTLR = new MyGrammerParser.MyGrammerParser(tokens);
	newAst = {};
	newAst.root = new Array();
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
	parserANTLR.removeErrorListeners();
	parserANTLR.addErrorListener(new ErrorListener());

	parserANTLR.buildParseTrees = true;

	var tree = parserANTLR.form();

	parseQuestions(tree);
}	

function parseQuestions(parseTree){
	

	var newDependencies = new Array();

	var Visitor = function(){
		MyGrammerVisitor.MyGrammerVisitor.call(this);
		return this;
	};

	Visitor.prototype = Object.create(MyGrammerVisitor.MyGrammerVisitor.prototype);

	Visitor.prototype.visitForm = function (ctx){
		for(var i=0;i<ctx.children.length;i++){
			this.visit(ctx.children[i]);
		}
		createAST();
		if(!checkCyclicDependencies()){
			renderQuestions();
	   		setHandlers();
	    	refreshGUI();	
		}
	};

	Visitor.prototype.visitOpExpr = function (ctx){
		console.log("A EXPR!");
	};

	Visitor.prototype.visitElsestmt = function (ctx){
		var oldDepencendy = newDependencies.pop();
		var newDependency = {};
		newDependency.condition = "!" + oldDepencendy.condition;
		newDependency.nodeType = "condition";
		if(newDependencies.length==0){
			newAst.root.push(newDependency);
			newDependencies.push(newDependency);
		}
		else{
			var currentDependency = newDependencies.pop();
			if(currentDependency.children == undefined){
				currentDependency.children = new Array();
			}
			currentDependency.children.push(newDependency);
			newDependencies.push(currentDependency);
			newDependencies.push(newDependency);
		}

		ctx.que.accept(this);	
	};

	Visitor.prototype.visitParenthesisExpr = function (ctx){
		return ctx.result;
	};

	Visitor.prototype.visitIfstmt = function (ifNode){

		console.log(ifNode.children[1]);
		var dependency = {};
		dependency.condition = ifNode.children[1].getText();
		//TODO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		dependency.newCond = ifNode.children[1].accept(this);
		dependency.nodeType = "condition";
		if(newDependencies.length==0){
			newAst.root.push(dependency);
			newDependencies.push(dependency);
		}
		else{
			var currentDependency = newDependencies.pop();
			if(currentDependency.children == undefined){
				currentDependency.children = new Array();
			}
			currentDependency.children.push(dependency);
			newDependencies.push(currentDependency);
			newDependencies.push(dependency);
		}

		
		//visit queries
		ifNode.children[2].accept(this);
		//visit possible else
		if(ifNode.children[3]!=undefined){
			ifNode.children[3].accept(this);
		}

		newDependencies.pop();
	};

	Visitor.prototype.visitQuestion = function (questionNode){
		var question = {};
	    question.nodeType = "question";
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

	    question.visible = true;
	    question.parent = newDependencies.slice(0);
	    
		
		if(newDependencies.length==0){
	    	newAst.root.push(question);
	    }
	    else{
	    	var dependency = newDependencies.pop();
	    	if(dependency.children == undefined){
	    		dependency.children = new Array();
	    	}
	    	dependency.children.push(question);
	    	newDependencies.push(dependency);
	    }
	};

	Visitor.prototype.visitQueries = function (ctx){
		for(var i=0;i<ctx.children.length;i++){
			ctx.children[i].accept(this);
		}
	}

	var visitor = new Visitor();
	parseTree.accept(visitor);



	var QuestionPrinter = function () {
    	MyGrammerListener.MyGrammerListener.call(this);
    	return this;
	};

	QuestionPrinter.prototype = Object.create(MyGrammerListener.MyGrammerListener.prototype);
	
	//done
	QuestionPrinter.prototype.enterIfstmt = function (ifNode) {

		var dependency = {};
		dependency.condition = ifNode.children[1].getText();
		dependency.nodeType = "condition";
		if(newDependencies.length==0){
			newAst.root.push(dependency);
			newDependencies.push(dependency);
		}
		else{
			var currentDependency = newDependencies.pop();
			if(currentDependency.children == undefined){
				currentDependency.children = new Array();
			}
			currentDependency.children.push(dependency);
			newDependencies.push(currentDependency);
			newDependencies.push(dependency);
		}
	};

	//done
	QuestionPrinter.prototype.exitIfstmt = function (ifNode) {
		newDependencies.pop();
	};

	//done
	QuestionPrinter.prototype.enterElsestmt = function(ctx) {
		var oldDepencendy = newDependencies.pop();
		var newDependency = {};
		newDependency.condition = "!" + oldDepencendy.condition;
		newDependency.nodeType = "condition";
		if(newDependencies.length==0){
			newAst.root.push(newDependency);
			newDependencies.push(newDependency);
		}
		else{
			var currentDependency = newDependencies.pop();
			if(currentDependency.children == undefined){
				currentDependency.children = new Array();
			}
			currentDependency.children.push(newDependency);
			newDependencies.push(currentDependency);
			newDependencies.push(newDependency);
		}
	};


	QuestionPrinter.prototype.exitQuestion = function (questionNode) {
	    var question = {};
	    question.nodeType = "question";
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

	    question.visible = true;
	    question.parent = newDependencies.slice(0);
	    
		
		if(newDependencies.length==0){
	    	newAst.root.push(question);
	    }
	    else{
	    	var dependency = newDependencies.pop();
	    	if(dependency.children == undefined){
	    		dependency.children = new Array();
	    	}
	    	dependency.children.push(question);
	    	newDependencies.push(dependency);
	    }
	};

	QuestionPrinter.prototype.exitForm = function (ctx) {
		createAST();
		if(!checkCyclicDependencies()){
			renderQuestions();
	   		setHandlers();
	    	refreshGUI();	
		}
	    
	};

	//var printer = new QuestionPrinter();
	//antlr4.tree.ParseTreeWalker.DEFAULT.walk(printer, parseTree);
}

function createAST(){
	var texts = new Set();
	var labels = new Set();

	var stack = new Array();
	for(var i=0;i<newAst.root.length;i++){
		stack.push(newAst.root[i]);
	}

	while(stack.length>0){
		var currentNode = stack.pop();
		if(currentNode.nodeType=="question"){
			if(labels.has(currentNode.label)){
				errors.add("ERROR: " + "Label '" + currentNode.label + "' is already defined");
				fillPanel("error", errors, true);
			}
			else{
				if(texts.has(currentNode.text)){
					warnings.add("WARNING: " + "Text '" + currentNode.text + "' is already defined");
					fillPanel("warning", warnings);
				}

				labels.add(currentNode.label);
				texts.add(currentNode.text);
			}
		}
		else if(currentNode.children != undefined){
			for (var j = 0; j<currentNode.children.length; j++) {
				stack.push(currentNode.children[j]);
			}
		}
	}
}


function setASTQuestionValue(label, value){
	var stack = new Array();
	for(var i=0;i<newAst.root.length;i++){
		stack.push(newAst.root[i]);
	}

	while(stack.length>0){
		var currentNode = stack.pop();
		if(currentNode.nodeType=="question" && currentNode.label == label){
			if(currentNode.type == "integer"){
				currentNode.value = parseInt(value);
			}
			else if(currentNode.type == "decimal" || currentNode.type == "money" || currentNode.type == "currency"){
				currentNode.value = parseFloat(value);
			}
			else{
				currentNode.value = value;
			}
			break;
		}
		else if(currentNode.children != undefined){
			for (var j = 0; j<currentNode.children.length; j++) {
				stack.push(currentNode.children[j]);
			}
		}
	}
}

function getQuestion(label){
	var stack = new Array();
	for(var i=0;i<newAst.root.length;i++){
		stack.push(newAst.root[i]);
	}

	while(stack.length>0){
		var currentNode = stack.pop();
		if(currentNode.nodeType=="question" && currentNode.label == label){
			return currentNode;
		}
		else if(currentNode.children != undefined){
			for (var j = 0; j<currentNode.children.length; j++) {
				stack.push(currentNode.children[j]);
			}
		}
	}

	return undefined;
}

function resetQuestionVisibility(){
	var stack = new Array();
	for(var i=0;i<newAst.root.length;i++){
		stack.push(newAst.root[i]);
	}

	while(stack.length>0){
		var currentNode = stack.pop();
		if(currentNode.nodeType=="question"){
			currentNode.visible = false;
		}
		else if(currentNode.children != undefined){
			for (var j = 0; j<currentNode.children.length; j++) {
				stack.push(currentNode.children[j]);
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

//TODO strings
function evaluate(statement){
	var evalStmt = "";
	var labels = getQuestionLabels();
	labels = labels.sort().reverse();
	for(var i=0;i<labels.length;i++){
		var regexObj = new RegExp(labels[i],"g");
		statement = statement.replace(regexObj, getQuestionValue(labels[i]));
	}

	var jisonErrorListener = function(str, hash){
		errors.add(str);
  		fillPanel("error", errors, true);
	};
	parser.yy.parseError = jisonErrorListener;

	var parseResponse = parser.parse(statement);
	return parseResponse;

}


function checkCyclicDependencies(){
	var questionLabels = getQuestionLabels();
	var questionDependencies = new Array();
	for(var i = 0;i<questionLabels.length;i++){
		questionDependencies[questionLabels[i]] = getQuestionsInSubtree(getQuestion(questionLabels[i]));
	}
	console.log(newAst);

	var detectedDepencendies = false;
	for(var i=0;i<questionLabels.length;i++){
		var dependencies = questionDependencies[questionLabels[i]];
		for(var j=0;j<dependencies.length;j++){
			var dependency = dependencies[j];
			if(questionDependencies[dependency].length>0){
				var otherDependencies = questionDependencies[dependency];
				for(var k=0;k<otherDependencies.length;k++){
					if(otherDependencies[k] == questionLabels[i]){
						detectedDepencendies = true;
						errors.add("Error: Cyclic dependency detected for questions " + questionLabels[i] + " and " + dependency);
					}
				}
			}
		}
	}

	if(detectedDepencendies){
		fillPanel("error", errors, true);
	}

	return detectedDepencendies;
}

function getQuestionsInSubtree(node){
	if(node.parent.length==0){
		return [];
	}
	else{
		var labels = [];
		for(var i=0;i<node.parent.length;i++){
			var parent = node.parent[i];
			labels = labels.concat(getLabelsInStatement(parent.condition));
		}
		return labels;
	}
}

function getLabelsInStatement(statement){
	var allLabels = getQuestionLabels();
	allLabels = allLabels.sort().reverse();
	var labels = [];
	for(var i=0;i<allLabels.length;i++){
		if(statement.indexOf(allLabels[i])>-1){
			labels.push(allLabels[i]);
			var regexObj = new RegExp(labels[i],"g");
			statement = statement.replace(regexObj, "");	
		}
	}
	
	return labels;
}


function refreshGUI(){
	$(".questionDiv").hide();
	resetQuestionVisibility();
	var stack = new Array();
	for(var i=0;i<newAst.root.length;i++){
		stack.push(newAst.root[i]);
	}

	while(stack.length>0){
		var currentNode = stack.pop();
		if(currentNode.nodeType=="question"){
			
			if(currentNode.expr != undefined){
				currentNode.value = evaluate(currentNode.expr);
			}

			currentNode.visible = true;
			$(".questionDiv[label='"+currentNode.label+"']").show();
			$("input[name='"+currentNode.label+"']").val(currentNode.value);

		}
		else if(currentNode.nodeType == "condition"){
			var evalResult = evaluate(currentNode.condition);
			if(typeof evalResult !== "boolean"){
  				errors.add("ERROR: Condition '"+currentNode.condition+"' is not boolean");
  				fillPanel("error", errors, true);
			}
			else if (evalResult) {
				for (var j = 0; j<currentNode.children.length; j++) {
					stack.push(currentNode.children[j]);
				}
			}
		}
	}
}

function getQuestionLabels(){
	var labels = new Array();
	var stack = new Array();
	for(var i=0;i<newAst.root.length;i++){
		stack.push(newAst.root[i]);
	}

	while(stack.length>0){
		var currentNode = stack.pop();
		if(currentNode.nodeType=="question"){
			labels.push(currentNode.label);
		}
		else if(currentNode.children != undefined){
			for (var j = 0; j<currentNode.children.length; j++) {
				stack.push(currentNode.children[j]);
			}
		}
	}
	return labels;
}

function getQuestionValue(label){
	var stack = new Array();
	for(var i=0;i<newAst.root.length;i++){
		stack.push(newAst.root[i]);
	}

	while(stack.length>0){
		var currentNode = stack.pop();
		if(currentNode.nodeType=="question" && currentNode.label == label){
			return currentNode.value;
		}
		else if(currentNode.children != undefined){
			for (var j = 0; j<currentNode.children.length; j++) {
				stack.push(currentNode.children[j]);
			}
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

function renderQuestions(){
	var questions = new Array();

	var stack = new Array();
	for(var i=0;i<newAst.root.length;i++){
		stack.push(newAst.root[i]);
	}

	while(stack.length>0){
		var currentNode = stack.pop();
		if(currentNode.nodeType=="question"){
			questions.push(currentNode);
		}
		else if(currentNode.children != undefined){
			for (var j = 0; j<currentNode.children.length; j++) {
				stack.push(currentNode.children[j]);
			}
		}
	}

	questions = questions.reverse();

	var output = "";

	for(var i=0;i<questions.length;i++){
		output += generateQuestionHTML(questions[i]);
	}

	$("#output").html(output);
}

function fillPanel(panel, set, critical){
	var html = "<ul>";

	set.forEach(function(value) {
		value = value.replace(/\n/g, "<br/>");
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

	var stack = new Array();
	for(var i=0;i<newAst.root.length;i++){
		stack.push(newAst.root[i]);
	}

	while(stack.length>0){
		var currentNode = stack.pop();
		if(currentNode.nodeType=="question" && currentNode.visible){
			var answer = {};
			answer.questionLabel = currentNode.label;
			answer.questionText = currentNode.text;
			answer.questionType = currentNode.type;
			answer.value = currentNode.value;
			answers.push(answer);
		}
		else if(currentNode.children != undefined){
			for (var j = 0; j<currentNode.children.length; j++) {
				stack.push(currentNode.children[j]);
			}
		}
	}

	answers = answers.reverse();

	var blob = new Blob([JSON.stringify(answers, null, 2)], {type: "text/plain;charset=utf-8"});
	saveAs(blob, "answers.txt");
	
});

//Start
initiate($("#input").val());