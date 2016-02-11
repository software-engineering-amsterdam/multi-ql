"use strict";

var antlr4 = require('antlr4/index');
var MyGrammerLexer = require('MyGrammerLexer');
var MyGrammerParser = require('MyGrammerParser');
var MyGrammerListener = require('MyGrammerListener');
var MyGrammerVisitor = require('MyGrammerVisitor');

var newAst;
var ast;
var errors;
var warnings;

var FormNode = function(label, queries){
	this.label = label;
	this.queries = queries;
};

var QuestionNode = function(text, label, type, computedExpr){
	this.text = text;
	this.label = label;
	this.type = type;
	this.computedExpr = computedExpr;
	this.visible = true;

	if(this.type == "integer"){
		this.value = 0;
	}
	else if(this.type == "float" || this.type == "money" || this.type == "currency"){
		this.value = 0.0;
	}
	else if(this.type == "boolean"){
		this.value = false;
	}
	else{
		this.value = "";
	}

};

var ConditionNode = function(ifExpr, queries, elseQueries){
	this.condition = ifExpr;
	this.queries = queries;
	this.elseQueries = elseQueries;
};

var LabelNode = function(label){
	this.label = label;
};

var ExpressionNode = function (expr){
	this.expr = expr;
};

var OperatorExpressionNode = function (left, op, right){
	this.left = left;
	this.op = op;
	this.right = right;
}

var NotExpression = function (expr){
	this.expr = expr;
}

function initiate(inputString){

	var characters = new antlr4.InputStream(inputString);
	var lexer = new MyGrammerLexer.MyGrammerLexer(characters);
	var tokens  = new antlr4.CommonTokenStream(lexer);
	var parserANTLR = new MyGrammerParser.MyGrammerParser(tokens);

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
	lexer.addErrorListener(new ErrorListener());
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
		ast = ctx.FormNode;
		if(preformASTCheck()){
			renderQuestions();
	   		setHandlers();
	    	refreshGUI();	
		}
	};

	var visitor = new Visitor();
	parseTree.accept(visitor);

}

//done
function preformASTCheck(){
	var texts = new Set();
	var labels = new Set();

	var stack = new Array();
	var noErrors = true;

	for(var i=0;i<ast.queries.length;i++){
		stack.push(ast.queries[i]);
	}

	while(stack.length>0){
		var currentNode = stack.pop();
		if(currentNode instanceof QuestionNode){
			if(labels.has(currentNode.label)){
				errors.add("ERROR: " + "Label '" + currentNode.label + "' is already defined");
				fillPanel("error", errors, true);
				noErrors = true;
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
		else if(currentNode instanceof ConditionNode){
			for (var i=0; i<currentNode.queries.length; i++) {
				stack.push(currentNode.queries[i]);
			}
			if(currentNode.elseQueries != undefined){
				for (var i=0; i<currentNode.elseQueries.length; i++) {
					stack.push(currentNode.elseQueries[i]);
				}
			}
		}
	}
	return noErrors;
}

//done
function setASTQuestionValue(label, value){
	getQuestion(label).value = value;
}

//done
function getQuestion(label){
	var stack = new Array();
	for(var i=0;i<ast.queries.length;i++){
		stack.push(ast.queries[i]);
	}

	while(stack.length>0){
		var currentNode = stack.pop();
		if(currentNode instanceof QuestionNode){
			if(currentNode.label == label){
				return currentNode;
			}
		}
		else if(currentNode instanceof ConditionNode){
			for (var i=0; i<currentNode.queries.length; i++) {
				stack.push(currentNode.queries[i]);
			}
			if(currentNode.elseQueries != undefined){
				for (var i=0; i<currentNode.elseQueries.length; i++) {
					stack.push(currentNode.elseQueries[i]);
				}
			}
		}
	}

	return undefined;
}

//done
function resetQuestionVisibility(){
	var stack = new Array();
	for(var i=0;i<ast.queries.length;i++){
		stack.push(ast.queries[i]);
	}

	while(stack.length>0){
		var currentNode = stack.pop();
		if(currentNode instanceof QuestionNode){
			currentNode.visible = false;
		}
		else if(currentNode instanceof ConditionNode){
			for (var i=0; i<currentNode.queries.length; i++) {
				stack.push(currentNode.queries[i]);
			}
			if(currentNode.elseQueries != undefined){
				for (var i=0; i<currentNode.elseQueries.length; i++) {
					stack.push(currentNode.elseQueries[i]);
				}
			}
		}
	}
}

//done
function setHandlers(){
	$("input").change(function(){
		var label = $(this).attr("name");
		if($(this).attr("type")=="checkbox"){
			getQuestion(label).value = $(this).is(":checked");
		}
		else{
			getQuestion(label).value = $(this).val();
		}
		refreshGUI();
	});
}

//done
//TODO strings
function evaluateStmt(statement){
	if(statement instanceof OperatorExpressionNode){
		var left = evaluateStmt(statement.left);
		var right = evaluateStmt(statement.right);

		switch(statement.op){
			case "+": 
				return left+right;
			case "-": 
				return left-right;
			case "*": 
				return left*right;
			case "/": 
				return left/right;
			case "<": 
				return left<right;
			case ">": 
				return left>right;
			case "<=": 
				return left<=right;
			case ">=": 
				return left>=right;
			case "==": 
				return left==right;
			case "!=": 
				return left!=right;
			case "&&": 
				return left&&right;
			case "||": 
				return left||right;
		}
	}
	else if(statement instanceof NotExpression){
		return !(evaluateStmt(statement.expr));
	}
	else if(statement instanceof ExpressionNode){
		return evaluateStmt(statement.expr);
	}
	else if(statement instanceof LabelNode){
		return getQuestion(statement.label).value;
	}
	else{
		return statement;
	}
}

//todo
function checkCyclicDependencies(){
	var questionLabels = getQuestionLabels();
	var questionDependencies = new Array();
	for(var i = 0;i<questionLabels.length;i++){
		questionDependencies[questionLabels[i]] = getQuestionsInSubtree(getQuestion(questionLabels[i]));
	}
	console.log("Generated AST: ");
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

//todo
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

//todo
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

//done
function getLabelsInStatement(statement){
	var labels = [];
	
	if(statement instanceof LabelNode){
		return [statement.label];
	}
	else if(statement instanceof OperatorExpressionNode){	
		labels = labels.concat(getLabelsInStatement(statement.left));
		labels = labels.concat(getLabelsInStatement(statement.right));
	}
	else if(statement instanceof NotExpression || statement instanceof ExpressionNode){
		labels = labels.concat(getLabelsInStatement(statement.expr));
	}
	else {
		return [];
	}

	return labels;
}

//done
function refreshGUI(){
	$(".questionDiv").hide();
	resetQuestionVisibility();

	var stack = new Array();
	for(var i=0;i<ast.queries.length;i++){
		stack.push(ast.queries[i]);
	}

	while(stack.length>0){
		var currentNode = stack.pop();
		if(currentNode instanceof QuestionNode){
			if(currentNode.computedExpr != undefined){
				currentNode.value = evaluateStmt(currentNode.computedExpr);
			}
			currentNode.visible = true;
			$(".questionDiv[label='"+currentNode.label+"']").show();
			$("input[name='"+currentNode.label+"']").val(currentNode.value);
		}
		else if(currentNode instanceof ConditionNode){
			var evalResult = evaluateStmt(currentNode.condition);
			if(typeof evalResult !== "boolean"){
  				errors.add("ERROR: Condition '"+currentNode.conditionTxt+"' is not boolean");
  				fillPanel("error", errors, true);
			}
			else if (evalResult) {
				for (var i=0; i<currentNode.queries.length; i++) {
					stack.push(currentNode.queries[i]);
				}	
			}
			else if(currentNode.elseQueries != undefined){
				for (var i=0; i<currentNode.elseQueries.length; i++) {
					stack.push(currentNode.elseQueries[i]);
				}	
			}
		}
	}
}

//done
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

	if(question.computedExpr != undefined){
		html += " disabled";
	}

	html += "/></label></div>";

	return html;
}

//done
function renderQuestions(){
	var questions = new Array();

	var stack = new Array();

	for(var i=0;i<ast.queries.length;i++){
		stack.push(ast.queries[i]);
	}

	while(stack.length>0){
		var currentNode = stack.pop();
		if(currentNode instanceof QuestionNode){
			questions.push(currentNode);
		}
		else if(currentNode instanceof ConditionNode){
			for (var i=0; i<currentNode.queries.length; i++) {
				stack.push(currentNode.queries[i]);
			}
			if(currentNode.elseQueries != undefined){
				for (var i=0; i<currentNode.elseQueries.length; i++) {
					stack.push(currentNode.elseQueries[i]);
				}
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

//done
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

//done
$("#generate").click(function(){
	initiate($("#input").val());
});

//done
$("#save").click(function(){
	var answers = new Array();


	var stack = new Array();
	
	for(var i=0;i<ast.queries.length;i++){
		stack.push(ast.queries[i]);
	}

	while(stack.length>0){
		var currentNode = stack.pop();
		if(currentNode instanceof QuestionNode && currentNode.visible){
			var answer = {};
			answer.questionLabel = currentNode.label;
			answer.questionText = currentNode.text;
			answer.questionType = currentNode.type;
			answer.value = currentNode.value;
			answers.push(answer);
		}
		else if(currentNode instanceof ConditionNode){
			for (var i=0; i<currentNode.queries.length; i++) {
				stack.push(currentNode.queries[i]);
			}
			if(currentNode.elseQueries != undefined){
				for (var i=0; i<currentNode.elseQueries.length; i++) {
					stack.push(currentNode.elseQueries[i]);
				}
			}
		}
	}

	answers = answers.reverse();

	var blob = new Blob([JSON.stringify(answers, null, 2)], {type: "text/plain;charset=utf-8"});
	saveAs(blob, "answers.txt");
	
});

//Start
initiate($("#input").val());