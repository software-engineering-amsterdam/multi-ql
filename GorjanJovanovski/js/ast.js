function initiate(inputString){

	var characters = new antlr4.InputStream(inputString);
	var lexer = new MyGrammerLexer.MyGrammerLexer(characters);
	var tokens  = new antlr4.CommonTokenStream(lexer);
	var parserANTLR = new MyGrammerParser.MyGrammerParser(tokens);

	warnings = new Set();
	errors = new Set();

	editor.getSession().clearAnnotations();

	resetInfoPanels();

	var ErrorListener = function() {
		antlr4.error.ErrorListener.call(this);
	  	return this;
	};
	ErrorListener.prototype = Object.create(antlr4.error.ErrorListener.prototype);
	ErrorListener.prototype.constructor = ErrorListener;
	ErrorListener.prototype.syntaxError = function(rec, sym, line, col, msg, e) {
	  	throwError(line, "Parse error: " + line + ":" + col + " - " + msg);
	};

	lexer.removeErrorListeners();
	parserANTLR.removeErrorListeners();
	lexer.addErrorListener(new ErrorListener());
	parserANTLR.addErrorListener(new ErrorListener());

	parserANTLR.buildParseTrees = true;
	var tree = parserANTLR.form();
	createAst(tree);
}	

function createAst(parseTree){
	
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
	    	console.log("Generated AST: ");
			console.log(ast);
		}
	};

	var visitor = new Visitor();
	parseTree.accept(visitor);
}

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
			if(currentNode.computedExpr != undefined && evaluateStmt(currentNode.computedExpr) == undefined){
				noErrors = false;	
			}
			questions.push(currentNode);
		}
		else if(currentNode instanceof ConditionNode){
			var evalResult = evaluateStmt(currentNode.condition);
			if(typeof evalResult !== "boolean"){
				noErrors = false;
  				throwError(currentNode.line, "Condition '"+currentNode.conditionTxt+"' is not boolean");
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
				noErrors = false;
				throwError(currentNode.line, "Label '" + currentNode.label + "' is already defined");
			}
			else{
				if(texts.has(currentNode.text)){
					throwWarning(currentNode.line, "Text '" + currentNode.text + "' is already defined");
				}

				labels.add(currentNode.label);
				texts.add(currentNode.text);
			}
	}
	return noErrors;
}

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

//TODO strings
function evaluateStmt(statement){
	console.log(statement);
	if(statement instanceof OperatorExpressionNode){
		var left = evaluateStmt(statement.left);
		var right = evaluateStmt(statement.right);

		switch(statement.op){
			case "+": 
				if(typeof left == "number" && typeof right == "number"){
					return left+right;
				}
				else{
					throwError(statement.line, "Expected left and right types of 'number', got '" + (typeof left) + "' and '" + (typeof right) + "'");
					return undefined;
				}
			case "-": 
				if(typeof left == "number" && typeof right == "number"){
					return left-right;
				}
				else{
					throwError(statement.line, "Expected left and right types of 'number', got '" + (typeof left) + "' and '" + (typeof right) + "'");
					return undefined;
				}
			case "*": 
				if(typeof left == "number" && typeof right == "number"){
					return left*right;
				}
				else{
					throwError(statement.line, "Expected left and right types of 'number', got '" + (typeof left) + "' and '" + (typeof right) + "'");
					return undefined;
				}
			case "/": 
				if(typeof left == "number" && typeof right == "number"){
					return left/right;
				}
				else{
					throwError(statement.line, "Expected left and right types of 'number', got '" + (typeof left) + "' and '" + (typeof right) + "'");
					return undefined;
				}
			case "<": 
				if(typeof left == "number" && typeof right == "number"){
					return left<right;
				}
				else{
					throwError(statement.line, "Expected left and right types of 'number', got '" + (typeof left) + "' and '" + (typeof right) + "'");
					return undefined;
				}
			case ">":
				if(typeof left == "number" && typeof right == "number"){
					return left>right;
				}
				else{
					throwError(statement.line, "Expected left and right types of 'number', got '" + (typeof left) + "' and '" + (typeof right) + "'");
					return undefined;
				}
			case "<=": 
				if(typeof left == "number" && typeof right == "number"){
					return left<=right;
				}
				else{
					throwError(statement.line, "Expected left and right types of 'number', got '" + (typeof left) + "' and '" + (typeof right) + "'");
					return undefined;
				}
			case ">=": 
				if(typeof left == "number" && typeof right == "number"){
					return left>=right;
				}
				else{
					throwError(statement.line, "Expected left and right types of 'number', got '" + (typeof left) + "' and '" + (typeof right) + "'");
					return undefined;
				}
			case "==": 
				if((typeof left == "number" && typeof right == "number") || (typeof left == "boolean" && typeof right == "boolean")){
					return left==right;
				}
				else{
					throwError(statement.line, "Expected left and right types of 'number' OR 'boolean', got '" + (typeof left) + "' and '" + (typeof right) + "'");
					return undefined;
				}
			case "!=": 
				if((typeof left == "number" && typeof right == "number") || (typeof left == "boolean" && typeof right == "boolean")){
					return left!=right;
				}
				else{
					throwError(statement.line, "Expected left and right types of 'number' OR 'boolean', got '" + (typeof left) + "' and '" + (typeof right) + "'");
					return undefined;
				}
			case "&&": 
				if(typeof left == "boolean" && typeof right == "boolean"){
					return left&&right;
				}
				else{
					throwError(statement.line, "Expected left and right types of 'boolean', got '" + (typeof left) + "' and '" + (typeof right) + "'");
					return undefined;
				}
			case "||": 
				if(typeof left == "boolean" && typeof right == "boolean"){
					return left||right;
				}
				else{
					throwError(statement.line, "Expected left and right types of 'boolean', got '" + (typeof left) + "' and '" + (typeof right) + "'");
					return undefined;
				}
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
			throwError(statement.line, "Question label '" + statement.label + "'' is undefined");
			return undefined;
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
						throwError(-1, "Cyclic dependency detected for questions " + questionLabels[i] + " and " + dependency);
					}
				}
			}
		}
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

//todo
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

function throwError(line, errorMsg){
	renderError(line, {line: line, msg: errorMsg});
}

function throwWarning(line, warningMsg){
	renderWarning(line, {line: line, msg: errorMsg});
}