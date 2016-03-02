/* ---- VALUES ---- */

class Listener {
	constructor() {
		this.listenerMap = [];
	}

	register(label, questionNode) {
		if (this.listenerMap[label] !== undefined) {
			this.listenerMap[label].push(questionNode);
		}
		else {
			this.listenerMap[label] = [questionNode];
		}
	}

	notify(label, value) {
		if (this.listenerMap[label] !== undefined) {
			for (var i = 0; i < this.listenerMap[label].length; i++) {
				this.listenerMap[label][i].notify(label, value);
			}
		}
	}
}


/* ---- ENVIRONMENT ---- */

class Environment {
	constructor() {
		this.valueMap = [];
	}

	setValue(label, value) {
		this.valueMap[label] = value;
	}

	getValue(label) {
		return this.valueMap[label];
	}

}

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
		this.listener = new Listener();
		this.setQuestionListeners();
	}

	setEnvironment(environment) {
		this.environment = environment;
		this.setQuestionEnvironment();
	}

	getQuestion(label) {
		return this.transverseAST((questionNode) => {
			if (questionNode.label === label) {
				return questionNode;
			}
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
		if (evaluateConditions === undefined) {
			evaluateConditions = false;
		}
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
				if (evaluateConditions === true && currentNode.condition.compute(this.environment) === true) {
					for (i = 0; i < currentNode.ifBlock.length; i++) {
						queue.push(currentNode.ifBlock[i]);
					}
				}
				if (currentNode.elseBlock !== undefined && evaluateConditions === true && currentNode.condition.compute(this.environment) === false) {
					for (i = 0; i < currentNode.elseBlock.length; i++) {
						queue.push(currentNode.elseBlock[i]);
					}
				}
			}
		}
	}

	setQuestionEnvironment() {
		this.transverseAST((questionNode) => {
				questionNode.setEnvironment(this.environment);

			},
			(conditionNode) => {
				conditionNode.setEnvironment(this.environment);
			}
		);
	}

	setQuestionListeners() {
		this.transverseAST((questionNode) => {
			var dependencies = questionNode.getDependencies();
			for (var i = 0; i < dependencies.length; i++) {
				this.listener.register(dependencies[i], questionNode);
			}

		});
	}

	//
	//setQuestionReferences() {
	//	this.transverseAST((questionNode) => {
	//		if (questionNode instanceof ComputedQuestionNode) {
	//			this.addQuestionReferenceToExpr(questionNode.computedExpr);
	//		}
	//	}, (conditionNode) => {
	//		this.addQuestionReferenceToExpr(conditionNode.condition);
	//	});
	//}
	//
	//addQuestionReferenceToExpr(expression) {
	//	if (expression instanceof NotExpression) {
	//		this.addQuestionReferenceToExpr(expression.expr);
	//	}
	//	else if (expression instanceof OperatorExpressionNode) {
	//		this.addQuestionReferenceToExpr(expression.left);
	//		this.addQuestionReferenceToExpr(expression.right);
	//	}
	//	else if (expression instanceof LabelNode) {
	//		var question = this.getQuestion(expression.label);
	//		if (question !== undefined) {
	//			expression.setQuestionReference(this.getQuestion(expression.label));
	//		}
	//		else {
	//			//TODO don't throw here?
	//			throwError(expression.line, "Question label " + expression.label + " is undefined");
	//		}
	//	}
	//}
}

class QuestionNode {
	constructor(text, label, type, line) {
		this.text = text;
		this.label = label;
		this.type = type;
		this.line = line;
		//this.value = this.type.parseValue(this.type.defaultValue());
	}

	notify(label, value) {
		if (this.label === label) {
			//this.value = this.type.parseValue(value);
			this.environment.setValue(this.label, this.type.parseValue(value));
		}
	}

	getDependencies() {
		return [this.label];
	}

	setEnvironment(environment) {
		this.environment = environment;
		this.environment.setValue(this.label, this.type.parseValue(this.type.defaultValue()));
		console.log(this.label + " " + this.type.parseValue(this.type.defaultValue()));
		console.log(this.environment);
	}

}

class ComputedQuestionNode extends QuestionNode {
	constructor(text, label, type, line, computedExpr) {
		super(text, label, type, line);
		this.computedExpr = computedExpr;
	}

	getDependencies() {
		return super.getDependencies().concat(this.getLabelsInExpression());
	}

	getLabelsInExpression() {
		return this.computedExpr.getLabelsInExpression();
	}

	notify(label, value) {
		super.notify(this.label, this.computedExpr.compute(this.environment));
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

	setEnvironment(environment) {
		this.condition.setEnvironment(environment);
	}
}

class NotExpression {
	constructor(expr, line) {
		this.expr = expr;
		this.line = line;
	}

	setEnvironment(environment) {
		this.environment = environment;
	}

	compute(environment) {
		return !this.expr.compute(environment);
	}

	toString() {
		return "!" + this.expr.toString();
	}

	getLabelsInExpression() {
		return this.expr.getLabelsInExpression();
	}

}

class OperatorExpressionNode {
	constructor(left, opNode, right, line) {
		this.left = left;
		this.opNode = opNode;
		this.right = right;
		this.line = line;
	}

	setEnvironment(environment) {
		this.environment = environment;
	}

	compute(environment) {
		return this.opNode.compute(this.left, this.right, environment);
	}

	toString() {
		return this.left.toString() + this.opNode.toString() + this.right.toString();
	}

	getLabelsInExpression() {
		return this.left.getLabelsInExpression().concat(this.right.getLabelsInExpression());
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

	compute(left, right, environment) {
		if (this.validateArguments(left.compute(environment), right.compute(environment))) {
			switch (this.op) {
				case "*":
					return left.compute(environment) * right.compute(environment);
				case "/":
					return left.compute(environment) / right.compute(environment);
				case "+":
					return left.compute(environment) + right.compute(environment);
				case "-":
					return left.compute(environment) - right.compute(environment);
				case "<":
					return left.compute(environment) < right.compute(environment);
				case "<=":
					return left.compute(environment) <= right.compute(environment);
				case ">":
					return left.compute(environment) > right.compute(environment);
				case ">=":
					return left.compute(environment) >= right.compute(environment);
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

	compute(left, right, environment) {
		if (this.validateArguments(left.compute(environment), right.compute(environment))) {
			switch (this.op) {
				case "&&":
					return left.compute(environment) && right.compute(environment);
				case "||":
					return left.compute(environment) || right.compute(environment);
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

	compute(left, right, environment) {

		if (this.validateArguments(left.compute(environment), right.compute(environment))) {
			switch (this.op) {
				case "==":
					return left.compute(environment) === right.compute(environment);
				case "!=":
					return left.compute(environment) !== right.compute(environment);
				default:
					return undefined;
			}
		}
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

	compute(environment) {
		return environment.getValue(this.label);
	}

	getLabelsInExpression() {
		return [this.label];
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

	getLabels() {
		return [];
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