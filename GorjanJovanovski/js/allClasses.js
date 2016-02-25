/* ---- TYPES ---- */

class NumberType {
	parseValue(value) {
		return parseInt(value);
	}

	defaultValue() {
		return 0;
	}

	toString() {
		return 'number';
	}
}

class DecimalType {
	parseValue(value) {
		return parseFloat(value);
	}

	defaultValue() {
		return 0.0;
	}

	toString() {
		return 'number';
	}
}

class BooleanType {
	parseValue(value) {
		if (typeof value === 'boolean')
			return value;
		return value === "true";
	}

	defaultValue() {
		return false;
	}

	toString() {
		return 'boolean';
	}
}

class StringType {
	parseValue(value) {
		return value + "";
	}

	defaultValue() {
		return "";
	}

	toString() {
		return 'string';
	}
}

/* ---- FORMS ---- */

class FormNode {

	constructor(label, block) {
		this.label = label;
		this.block = block;

		this.setQuestionReferences();
	}

	getQuestion(label) {
		return this.transverseAST((questionNode) => {
			if (questionNode.label === label) {
				return questionNode;
			}
		});
	}

	resetQuestionVisibility() {
		this.transverseAST((questionNode) => {
			questionNode.visible = false;
		});
	}

	getAnswerList() {
		var answerList = new AnswerList();

		this.transverseAST((questionNode) => {
			answerList.addQuestion(questionNode);
		}, undefined, true);

		return answerList;
	}

	transverseAST(questionFunction, conditionFunction, evaluateConditions) {
		var queue = [];

		for (var i = 0; i < this.block.length; i++) {
			queue.push(this.block[i]);
		}
		while (queue.length > 0) {
			var currentNode = queue.shift();
			var result;
			if (currentNode instanceof QuestionNode && questionFunction !== undefined) {
				result = questionFunction(currentNode);
				if (result !== undefined) {
					return result;
				}
			}
			else if (currentNode instanceof ConditionNode) {
				if (conditionFunction !== undefined) {
					result = conditionFunction(currentNode);
					if (result !== undefined) {
						return result;
					}
				}
				if (evaluateConditions === undefined || (evaluateConditions === true && currentNode.condition.compute() === true)) {
					for (i = 0; i < currentNode.ifBlock.length; i++) {
						queue.push(currentNode.ifBlock[i]);
					}
				}
				if (currentNode.elseBlock !== undefined && (evaluateConditions === undefined ||
					(evaluateConditions === true && currentNode.condition.compute() === false))) {
					for (i = 0; i < currentNode.elseBlock.length; i++) {
						queue.push(currentNode.elseBlock[i]);
					}
				}
			}
		}
	}

	setQuestionReferences() {
		this.transverseAST((questionNode) => {
			if (questionNode instanceof ComputedQuestionNode) {
				this.addQuestionReferenceToExpr(questionNode.computedExpr);
			}
		}, (conditionNode) => {
			this.addQuestionReferenceToExpr(conditionNode.condition);
		});
	}

	addQuestionReferenceToExpr(expression) {
		if (expression instanceof NotExpression) {
			this.addQuestionReferenceToExpr(expression.expr);
		}
		else if (expression instanceof OperatorExpressionNode) {
			this.addQuestionReferenceToExpr(expression.left);
			this.addQuestionReferenceToExpr(expression.right);
		}
		else if (expression instanceof LabelNode) {
			var question = this.getQuestion(expression.label);
			if (question !== undefined) {
				expression.setQuestionReference(this.getQuestion(expression.label));
			}
			else {
				//TODO don't throw here?
				throwError(expression.line, "Question label " + expression.label + " is undefined");
			}
		}
	}
}

class QuestionNode {
	constructor(text, label, type, line) {
		this.text = text;
		this.label = label;
		this.type = type;
		this.visible = false;
		this.line = line;

		this.setValue(type.defaultValue());
	}

	setValue(value) {
		this.value = this.type.parseValue(value);
	}
}

class ComputedQuestionNode extends QuestionNode {
	constructor(text, label, type, line, computedExpr) {
		super(text, label, type, line);
		this.computedExpr = computedExpr;
	}
}

class ConditionNode {
	constructor(ifExpr, ifBlock, line, elseBlock) {
		this.condition = ifExpr;
		this.ifBlock = ifBlock;
		this.elseBlock = elseBlock;
		this.line = line;
	}

	toString() {
		return this.condition.toString();
	}
}

class LabelNode {
	constructor(label, line) {
		this.label = label;
		this.line = line;
	}

	toString() {
		return this.label;
	}

	setQuestionReference(question) {
		this.question = question;
	}

	compute() {
		return this.question.value;
	}
}

class NotExpression {
	constructor(expr, line) {
		this.expr = expr;
		this.line = line;
	}

	compute() {
		return !this.expr.compute();
	}

	toString() {
		return "!" + this.expr.toString();
	}

}

class OperatorExpressionNode {
	constructor(left, opNode, right, line) {
		this.left = left;
		this.opNode = opNode;
		this.right = right;
		this.line = line;
	}

	compute() {
		return this.opNode.compute(this.left, this.right);
	}

	toString() {
		return this.left.toString() + this.opNode.toString() + this.right.toString();
	}
}

class OperatorNode {

	constructor(op, validArguments, line) {
		this.op = op;
		this.validArguments = validArguments;
		this.line = line;
	}

	toString() {
		return this.op;
	}

	validateArguments(left, right) {
		if ((typeof left === typeof right) && this.validArguments.indexOf(typeof left) !== -1) {
			return true;
		}
		else {
			//TODO don't throw here?
			throwError(this.line, "Statement '" + left + this.op.toString() + right + "' expecting left and right to be of " + this.validArguments + ", found " + typeof left + " and " + typeof right);
			return false;
		}
	}

}

class NumOperatorNode extends OperatorNode {
	constructor(op, line) {
		super(op, ["number"], line);
	}

	compute(left, right) {
		if (this.validateArguments(left.compute(), right.compute())) {
			switch (this.op) {
				case "*":
					return left.compute() * right.compute();
				case "/":
					return left.compute() / right.compute();
				case "+":
					return left.compute() + right.compute();
				case "-":
					return left.compute() - right.compute();
				case "<":
					return left.compute() < right.compute();
				case "<=":
					return left.compute() <= right.compute();
				case ">":
					return left.compute() > right.compute();
				case ">=":
					return left.compute() >= right.compute();
				default:
					return undefined;
			}
		}
	}
}

class BoolOperatorNode extends OperatorNode {
	constructor(op, line) {
		super(op, ["boolean"], line);
	}

	compute(left, right) {

		if (this.validateArguments(left.compute(), right.compute())) {
			switch (this.op) {
				case "&&":
					return left.compute() && right.compute();
				case "||":
					return left.compute() || right.compute();
				default:
					return undefined;
			}
		}
	}
}

class NumOrBoolOperatorNode extends OperatorNode {
	constructor(op, line) {
		super(op, ["number", "boolean"], line);
	}

	compute(left, right) {

		if (this.validateArguments(left.compute(), right.compute())) {
			switch (this.op) {
				case "==":
					return left.compute() === right.compute();
				case "!=":
					return left.compute() !== right.compute();
				default:
					return undefined;
			}
		}
	}
}

class LiteralNode {
	constructor(value) {
		this.value = value;
	}

	compute() {
		return this.value;
	}

	toString() {
		return this.value;
	}
}

/* ---- ANSWERS ---- */

class AnswerList {
	constructor() {
		this.answerList = [];
	}

	addQuestion(questionNode) {
		this.answerList.push(new AnswerListQuestion(questionNode));
	}

	toString() {
		return JSON.stringify(this.answerList, null, 2);
	}
}

class AnswerListQuestion {
	constructor(questionNode) {
		this.label = questionNode.label;
		this.text = questionNode.text;
		this.value = questionNode.value;
	}
}