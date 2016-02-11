
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
	  	editor.gotoLine(line, col);
	  	editor.getSession().setAnnotations([{
		  row: line-1,
		  text: msg,
		  type: "error" // also warning and information
		}]);
	  	errors.add({line:line, error: "[line " + line +"]: Parse error: " + line + ":" + col + " - " + msg});
	  	fillPanel("error", errors, true);
	};
	lexer.removeErrorListeners();
	parserANTLR.removeErrorListeners();
	lexer.addErrorListener(new ErrorListener());
	parserANTLR.addErrorListener(new ErrorListener());

	editor.getSession().clearAnnotations();

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
		console.log(ast);
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
	var questions = new Array();

	for(var i=0;i<ast.queries.length;i++){
		stack.push(ast.queries[i]);
	}

	while(stack.length>0){
		var currentNode = stack.pop();
		if(currentNode instanceof QuestionNode){
			evaluateStmt(currentNode.computedExpr);
			questions.push(currentNode);
		}
		else if(currentNode instanceof ConditionNode){
			var evalResult = evaluateStmt(currentNode.condition);
			if(typeof evalResult !== "boolean"){
  				errors.add({line: currentNode.line, error: "[line " + currentNode.line +"]: Condition '"+currentNode.conditionTxt+"' is not boolean"});
  				fillPanel("error", errors, true);
			}
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

	for(var i=0;i<questions.length;i++){
		var currentNode = questions[i];
		if(labels.has(currentNode.label)){
				errors.add({line:currentNode.line, error: "[line " + currentNode.line +"]: Label '" + currentNode.label + "' is already defined"});
				fillPanel("error", errors, true);
				noErrors = true;
			}
			else{
				if(texts.has(currentNode.text)){
					warnings.add({line: currentNode.line, error: "[line " + currentNode.line +"]: Text '" + currentNode.text + "' is already defined"});
					fillPanel("warning", warnings);
				}

				labels.add(currentNode.label);
				texts.add(currentNode.text);
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
		var question = getQuestion(statement.label);
		if(question==undefined){
			errors.add({line:statement.line, error: "[line " + statement.line +"]: Question label '" + statement.label + "'' is undefined"});
			fillPanel("error", errors, true);
			return;
		}
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
						errors.add({line: -1, error: "Cyclic dependency detected for questions " + questionLabels[i] + " and " + dependency});
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