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

	this.setValue = function (value) {
		if(this.type == "integer"){
			this.value = parseInt(value);
		}
		else if(this.type == "float" || this.type == "money" || this.type == "currency"){
			this.value = parseFloat(value);
		}
		else if(this.type == "boolean"){
			this.value = value;
		}
		else{
			this.value = value;
		}
	};

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
	this.elseQueries = elseQueries;
	this.line = line;


	this.toString = function(){
		return this.condition.toString();
	};
};

var LabelNode = function(label, line){
	this.label = label;
	this.line = line;
	this.toString = function(){
		return label;
	};
};

var ExpressionNode = function (expr, line){
	this.expr = expr;
	this.line = line;

	this.toString = function(){
		return expr.toString();
	};
};

var OperatorExpressionNode = function (left, op, right, line){
	this.left = left;
	this.op = op;
	this.right = right;
	this.line = line;

	this.toString = function(){
		return left.toString() + op + right.toString();
	};
}

var NotExpression = function (expr, line){
	this.expr = expr;
	this.line = line;

	this.toString = function(){
		return "!"+expr.toString();
	};
}