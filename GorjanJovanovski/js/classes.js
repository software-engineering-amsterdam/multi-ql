var FormNode = function(label, queries){
	this.label = label;
	this.queries = queries;
};

var QuestionNode = function(text, label, type, line, computedExpr){
	this.text = text;
	this.label = label;
	this.type = type;
	this.computedExpr = computedExpr;
	this.visible = true;
	this.line = line;

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

var ConditionNode = function(ifExpr, queries, line, elseQueries){
	this.condition = ifExpr;
	this.queries = queries;
	this.line = line;
	this.elseQueries = elseQueries;
};

var LabelNode = function(label, line){
	this.label = label;
	this.line = line;
};

var ExpressionNode = function (expr, line){
	this.expr = expr;
	this.line = line;
};

var OperatorExpressionNode = function (left, op, right, line){
	this.left = left;
	this.op = op;
	this.right = right;
	this.line = line;
}

var NotExpression = function (expr, line){
	this.expr = expr;
	this.line = line;
}