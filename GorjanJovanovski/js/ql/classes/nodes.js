class FormNode {

	constructor(label, block) {
		this.label = label;
		this.block = block;
		this.listener = new Listener();
		this.setQuestionListeners();
	}

	setEnvironment(environment) {
		this.environment = environment;
		this.transverseAST((questionNode) => {
				questionNode.setEnvironment(environment);
			},
			(conditionNode) => {
				conditionNode.setEnvironment(environment);
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

	getAnswerList() {
		var answerList = new AnswerList();
		this.transverseAST((questionNode) => {
			answerList.addQuestion(questionNode, this.environment);
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
				if (evaluateConditions === false || (evaluateConditions === true && currentNode.condition.compute(this.environment) === true)) {
					for (i = 0; i < currentNode.ifBlock.length; i++) {
						queue.push(currentNode.ifBlock[i]);
					}
				}
				if (currentNode.elseBlock !== undefined && (evaluateConditions === false || (evaluateConditions === true && currentNode.condition.compute(this.environment) === false))) {
					for (i = 0; i < currentNode.elseBlock.length; i++) {
						queue.push(currentNode.elseBlock[i]);
					}
				}
			}
		}
	}

	notify(label, value) {
		this.transverseAST((questionNode) => {
			if (questionNode.label === label) {
				questionNode.notify(value);
			}
		});
		this.listener.notify(label);
	}

}

class QuestionNode {
	constructor(text, label, type, line) {
		this.text = text;
		this.label = label;
		this.type = type;
		this.line = line;
	}

	notify(value) {
		this.environment.setValue(this.label, this.type.parseValue(value));
	}

	getDependencies() {
		return [];
	}

	setEnvironment(environment) {
		this.environment = environment;
		this.environment.setValue(this.label, this.type.parseValue(this.type.defaultValue()));
	}

	getTypeString() {
		return this.type.getTypeString();
	}

	checkExpressionType() {
		return true;
	}

	isExpressionDefined() {
		return true;
	}

	exprString() {
		return "";
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

	notify() {
		super.notify(this.computedExpr.compute(this.environment));
	}

	setEnvironment(environment) {
		super.setEnvironment(environment);
		this.computedExpr.setEnvironment(environment);
		this.notify();
	}

	checkExpressionType() {
		return typeof this.computedExpr.compute() === this.type.toString();
	}

	isExpressionDefined() {
		return this.computedExpr.compute() !== undefined;
	}

	exprString() {
		return this.computedExpr.toString();
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

	isExpressionDefined() {
		return this.condition.compute() !== undefined;
	}

	exprString() {
		return this.condition.toString();
	}

	checkExpressionType() {
		return typeof this.condition.compute() === "boolean";
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

	compute() {
		if (this.validateArguments()) {
			return !this.expr.compute(this.environment);
		}
	}

	toString() {
		return "!" + this.expr.toString();
	}

	getLabelsInExpression() {
		return this.expr.getLabelsInExpression();
	}

	validateArguments() {
		if (typeof this.expr.compute(this.environment) !== "boolean") {
			return false;
		}
		return true;
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

	compute() {
		return this.opNode.compute(this.left, this.right, this.environment);
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

	setEnvironment(environment) {

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

	setEnvironment(environment) {

	}

	getLabelsInExpression() {
		return [];
	}
}