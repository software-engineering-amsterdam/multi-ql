class FormNode{
	constructor(label, block) {
		this.label = label;
		this.block = block;
	}
}

class NumberType{
	parseValue(value){
		return parseInt(value);
	}
	defaultValue(){
		return 0;
	}
}

class DecimalType{
	parseValue(value){
		return parseFloat(value);
	}

	defaultValue(){
		return 0.0;
	}
}

class BooleanType{
	parseValue(value){
		if(typeof value == 'boolean')
			return value;
		return value=="true";
	}

	defaultValue(){
		return false;
	}
}

class StringType{
	parseValue(value){
		return value+"";
	}

	defaultValue(){
		return "";
	}
}

class QuestionNode{
	constructor(text, label, type, line){
		this.text = text;
		this.label = label;
		this.type = type;
		this.visible = false;
		this.line = line;

		this.setValue(type.defaultValue())
	}

	setValue(value){
		this.value = this.type.parseValue(value);
	}
}

class ComputedQuestionNode extends QuestionNode{
	constructor(text, label, type, line, computedExpr){
		super(text, label, type, line);
		this.computedExpr = computedExpr;		
	}
}

class ConditionNode{
	constructor(ifExpr, ifBlock, line, elseBlock) {
		this.condition = ifExpr;
		this.ifBlock = ifBlock;
		this.elseBlock = elseBlock;
		this.line = line;
	}

	toString(){
		return this.condition.toString();
	}
}

class LabelNode{
	constructor(label, line) {
		this.label = label;
		this.line = line;
	}

	toString(){
		return this.label;
	}

	compute(){
		var question = getQuestion(this.label);
		if(question == undefined){
			throwError(this.line, "Question label '" + this.label + "' is undefined");
			return undefined;
		}
		return getQuestion(this.label).value;
	}
}


class NotExpression{
	constructor(expr, line) {
		this.expr = expr;
		this.line = line;
	}

	compute(){
		return !this.expr.compute();
	}

	toString(){
		return "!"+expr.toString();
	}

}


class OperatorExpressionNode{
	constructor(left, opNode, right, line) {
		this.left = left;
		this.opNode = opNode;
		this.right = right;
		this.line = line;
	}

	compute(){
		return this.opNode.compute(this.left, this.right);
	}

	toString(){
		return this.left.toString() + this.opNode.toString() + this.right.toString();
	}
}

class OperatorNode{

	constructor(op, validArguments, line) {
		this.op = op;
		this.validArguments = validArguments;
		this.line = line;
	}

	toString(){
		return this.op;
	}

	validateArguments(left, right){
		if((typeof left == typeof right) && this.validArguments.indexOf(typeof left) != -1){
			return true;
		}
		else{
			throwError(this.line, "Statement '" + left + "" + this.op + "" + right + "' expecting left and right to be of " + this.validArguments + ", found " + typeof left + " and " + typeof right);
			return false;
		}
	}

}


class NumOperatorNode extends OperatorNode{
	constructor(op, line) {
		super(op, ["number"], line);
	}

	compute(left, right){
		if(this.validateArguments(left.compute(), right.compute())){
			switch(this.op){
				case "*": return left.compute() * right.compute();
				case "/": return left.compute() / right.compute();
				case "+": return left.compute() + right.compute();
				case "-": return left.compute() - right.compute();
				case "<": return left.compute() < right.compute();
				case "<=": return left.compute() <= right.compute();
				case ">": return left.compute() > right.compute();
				case ">=": return left.compute() >= right.compute();
				default: return undefined;
			}
		}
	}
}

class BoolOperatorNode extends OperatorNode{
	constructor(op, line) {
		super(op, ["boolean"], line);
	}

	compute(left, right){

		if(this.validateArguments(left.compute(), right.compute())){
			switch(this.op){
				case "&&": return left.compute() && right.compute();
				case "||": return left.compute() || right.compute();
				default: return undefined;
			}
		}
	}
}


class NumOrBoolOperatorNode extends OperatorNode{
	constructor(op, line) {
		super(op, ["number", "boolean"], line);
	}

	compute(left, right){

		if(this.validateArguments(left.compute(), right.compute())){
			switch(this.op){
				case "==": return left.compute() == right.compute();
				case "!=": return left.compute() != right.compute();
				default: return undefined;
			}
		}
	}
}


class LiteralNode{
	constructor(value){
		this.value = value;
	}

	compute(){
		return this.value;
	}

	toString(){
		return this.value;
	}
}
