import { NodeVisitor, RecursingVisitor } from 'src/ast';
import { TypeReceiver } from 'src/types';
import * as values from 'src/values';
import * as evaluation from 'src/evaluation';

class VariableMap {
	constructor() {
		this._map = {};
	}
	get(name) {
		return name in this._map ? this._map[name] : new values.UndefinedValue();
	}
	set(name, value) {
		this._map[name] = value;
	}
}

class ExprEvaluator extends NodeVisitor {
	evaluate(node, variables) {
		return node.accept(this, variables);
	}
	handleUnaryPrefixOperation(unaryExpressionNode, variables, evaluator) {
		let operandValue = unaryExpressionNode.operand.accept(this, variables);

		return evaluator.evaluate(operandValue);
	}
	visitNotNode(notNode, variables) {
		return this.handleUnaryPrefixOperation(notNode, variables, new evaluation.NotEvaluator());
	}
	visitNegationNode(negationNode, variables) {
		return this.handleUnaryPrefixOperation(negationNode, variables, new evaluation.NegationEvaluator());
	}
	handleInfixOperation(infixNode, variables, evaluator) {
		let leftOperandValue = infixNode.leftOperand.accept(this, variables),
			rightOperandValue = infixNode.rightOperand.accept(this, variables);

		return evaluator.evaluate(leftOperandValue, rightOperandValue);
	}
	visitAddNode(addNode, variables) {
		return this.handleInfixOperation(addNode, variables, new evaluation.AddEvaluator());
	}
	visitSubtractNode(subtractNode, variables) {
		return this.handleInfixOperation(subtractNode, variables, new evaluation.SubtractEvaluator());
	}
	visitMultiplyNode(multiplyNode, variables) {
		return this.handleInfixOperation(multiplyNode, variables, new evaluation.MultiplyEvaluator());
	}
	visitDivideNode(divideNode, variables) {
		return this.handleInfixOperation(divideNode, variables, new evaluation.DivideEvaluator());
	}
	visitGreaterNode(greaterNode, variables) {
		return this.handleInfixOperation(greaterNode, variables, new evaluation.GreaterEvaluator());
	}
	visitGreaterEqualNode(greaterEqualNode, variables) {
		return this.handleInfixOperation(greaterEqualNode, variables, new evaluation.GreaterEqualEvaluator());
	}
	visitLessNode(lessNode, variables) {
		return this.handleInfixOperation(lessNode, variables, new evaluation.LessEvaluator());
	}
	visitLessEqualNode(lessEqualNode, variables) {
		return this.handleInfixOperation(lessEqualNode, variables, new evaluation.LessEqualEvaluator());
	}
	visitEqualNode(equalNode, variables) {
		return this.handleInfixOperation(equalNode, variables, new evaluation.EqualEvaluator());
	}
	visitNotEqualNode(notEqualNode, variables) {
		return this.handleInfixOperation(notEqualNode, variables, new evaluation.NotEqualEvaluator());
	}
	visitAndNode(andNode, variables) {
		return this.handleInfixOperation(andNode, variables, new evaluation.AndEvaluator());
	}
	visitOrNode(orNode, variables) {
		return this.handleInfixOperation(orNode, variables, new evaluation.OrEvaluator());
	}
	visitLiteralNode(literalNode, variables) {
		return literalNode.value;
	}
	visitIdentifierNode(identifierNode, variables) {
		let name = identifierNode.name;

		return variables.get(name);
	}
}

class ValueUpdater extends NodeVisitor {
	constructor(exprEvaluator) {
		super();
		this.exprEvaluator = exprEvaluator;
	}
	update(node, variables) {
		let updated;
		do {
			updated = node.accept(this, variables);
		} while (updated === true);
	}
	visitFormNode(formNode, variables) {
		return formNode.block.accept(this, variables);
	}
	visitBlockNode(blockNode, variables) {
		return blockNode.statements.reduce((seed, statement) => seed || statement.accept(this, variables), false);
	}
	isIfConditionTrue(ifNode, variables) {
		return this.exprEvaluator.evaluate(ifNode.condition, variables).equals(new values.BooleanValue(true));
	}
	visitIfNode(ifNode, variables) {
		if (this.isIfConditionTrue(ifNode, variables)) {
			return ifNode.thenBlock.accept(this, variables);
		}
		return false;
	}
	visitIfElseNode(ifElseNode, variables) {
		return this.isIfConditionTrue(ifElseNode, variables) ? ifElseNode.thenBlock.accept(this, variables) : ifElseNode.elseBlock.accept(this, variables);
	}
	visitQuestionNode(questionNode, variables) {
		return false;
	}
	visitExprQuestionNode(exprQuestionNode, variables) {
		let name = exprQuestionNode.name,
			exprValue = this.exprEvaluator.evaluate(exprQuestionNode.expr, variables);

		if (exprValue.equals(variables.get(name))) {
			return false;
		}

		variables.set(name, exprValue);
		return true;
	}
}

class WidgetBuilder {
	constructor(elementFactory) {
		this.elementFactory = elementFactory;
	}
}

class BooleanCheckBoxWidgetBuilder extends WidgetBuilder {
	render(containerElement, value, updateCallback) {
		let inputElement = this.elementFactory.createElement('input');

		inputElement.setAttribute('type', 'checkbox');
		inputElement.checked = value.value;
		if (updateCallback) {
			inputElement.onchange = function () {
				updateCallback(new values.BooleanValue(inputElement.checked));
			};
		} else {
			inputElement.setAttribute('readonly', 'readonly');
		}
		containerElement.appendChild(inputElement);
	}
}

class StringInputWidgetBuilder extends WidgetBuilder {
	render(containerElement, value, updateCallback) {
		let inputElement = this.elementFactory.createElement('input');

		inputElement.setAttribute('type', 'text');
		inputElement.value = value.value;
		if (updateCallback) {
			inputElement.onchange = function () {
				updateCallback(new values.StringValue(inputElement.value));
			};
		} else {
			inputElement.setAttribute('readonly', 'readonly');
		}
		containerElement.appendChild(inputElement);
	}
}

class WidgetBuilderFactory extends TypeReceiver {
	constructor(elementFactory) {
		super();
		this.elementFactory = elementFactory;
	}
	createWidgetBuilder(type) {
		return type.dispatch(this);
	}
	receiveBoolean() {
		return new BooleanCheckBoxWidgetBuilder(this.elementFactory);
	}
	receiveString() {
		return new StringInputWidgetBuilder(this.elementFactory);
	}
	receiveInteger() {
		return new IntegerInputWidgetBuilder(this.elementFactory);
	}
	receiveFloat() {
		return new FloatInputWidgetBuilder(this.elementFactory);
	}
	receiveMoney() {
		return new MoneyInputWidgetBuilder(this.elementFactory);
	}
}

export class Renderer extends NodeVisitor {
	constructor(elementFactory) {
		super();
		this.elementFactory = elementFactory;
		this.exprEvaluator = new ExprEvaluator();
		this.valueUpdater = new ValueUpdater(this.exprEvaluator);
		this.widgetBuilderFactory = new WidgetBuilderFactory(elementFactory);
	}
	update(node, containerElement, variables, updateCallback) {
		while(containerElement.firstChild !== null) {
			containerElement.removeChild(containerElement.firstChild);
		}
		this.valueUpdater.update(node, variables);
		node.accept(this, containerElement, variables, updateCallback);
	}
	render(node, containerElement) {
		let variables = new VariableMap(),
			updateCallback = () => {
				this.update(node, containerElement, variables, updateCallback);
			};
		updateCallback();
	}
	visitFormNode(formNode, containerElement, variables, updateCallback) {
		let descriptionElement = this.elementFactory.createElement('h2');

		descriptionElement.textContent = formNode.description;
		containerElement.appendChild(descriptionElement);
		formNode.block.accept(this, containerElement, variables, updateCallback);
	}
	visitBlockNode(blockNode, containerElement, variables, updateCallback) {
		for (let statement of blockNode.statements) {
			statement.accept(this, containerElement, variables, updateCallback);
		}
	}
	isExprTrue(exprNode, variables) {
		return this.exprEvaluator.evaluate(exprNode, variables).equals(new values.BooleanValue(true));
	}
	visitIfNode(ifNode, containerElement, variables, updateCallback) {
		if (this.isExprTrue(ifNode.condition, variables)) {
			ifNode.thenBlock.accept(this, containerElement, variables, updateCallback);
		}
	}
	visitIfElseNode(ifElseNode, containerElement, variables, updateCallback) {
		if (this.isExprTrue(ifElseNode.condition, variables)) {
			ifElseNode.thenBlock.accept(this, containerElement, variables, updateCallback);
		} else {
			ifElseNode.elseBlock.accept(this, containerElement, variables, updateCallback);
		}
	}
	handleQuestion(questionNode, containerElement, variables, updateCallback, readOnly) {
		let questionContainerElement = this.elementFactory.createElement('div'),
			questionlabelElement = this.elementFactory.createElement('label');

		questionContainerElement.setAttribute('class', 'question');
		questionlabelElement.textContent = questionNode.description;
		questionContainerElement.appendChild(questionlabelElement);

		//widget = this.renderWidget(questionNode, questionContainerElement, scope);
		this.widgetBuilderFactory.createWidgetBuilder(questionNode.type, readOnly).render(questionContainerElement, variables.get(questionNode.name), updateCallback);
		containerElement.appendChild(questionContainerElement);
	}
	visitQuestionNode(questionNode, containerElement, variables, updateCallback) {
		this.handleQuestion(questionNode, containerElement, variables, function (value) {
			variables.set(questionNode.name, value);
			updateCallback();
		});
	}
	visitExprQuestionNode(exprQuestionNode, containerElement, variables, updateCallback) {
		this.handleQuestion(exprQuestionNode, containerElement, variables);
	}
}