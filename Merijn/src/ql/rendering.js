import * as _ from 'lodash';
import * as ast from 'src/ql/ast';
import * as types from 'src/ql/types';
import * as values from 'src/ql/values';
import * as evaluation from 'src/ql/evaluation';

class Observable {
	constructor() {
		this._listeners = [];
	}
	listen(listener) {
		this._listeners.push(listener);
	}
	notifyListeners() {
		for (let listener of this._listeners) {
			listener();
		}
	}
}

class Variable extends Observable {
	constructor() {
		super();
		this._value = new values.UndefinedValue();
	}
	getValue() {
		return this._value;
	}
	setValue(value) {
		if (!this._value.equals(value)) {
			this._value = value;
			this.notifyListeners();
		}
	}
}

class VariableMap {
	constructor() {
		this._variables = {};
	}
	get(name) {
		if (!_.has(this._variables, name)) {
			this._variables[name] = new Variable();
		}
		return this._variables[name];
	}
}

class ExprEvaluator extends ast.NodeVisitor {
	evaluate(node, variableMap) {
		return node.accept(this, variableMap);
	}
	handleUnaryPrefixOperation(unaryExpressionNode, variableMap, evaluator) {
		let operandValue = unaryExpressionNode.operand.accept(this, variableMap);

		return evaluator.evaluate(operandValue);
	}
	visitNotNode(notNode, variableMap) {
		return this.handleUnaryPrefixOperation(notNode, variableMap, new evaluation.NotEvaluator());
	}
	visitNegationNode(negationNode, variableMap) {
		return this.handleUnaryPrefixOperation(negationNode, variableMap, new evaluation.NegationEvaluator());
	}
	handleInfixOperation(infixNode, variableMap, evaluator) {
		let leftOperandValue = infixNode.leftOperand.accept(this, variableMap),
			rightOperandValue = infixNode.rightOperand.accept(this, variableMap);

		return evaluator.evaluate(leftOperandValue, rightOperandValue);
	}
	visitAddNode(addNode, variableMap) {
		return this.handleInfixOperation(addNode, variableMap, new evaluation.AddEvaluator());
	}
	visitSubtractNode(subtractNode, variableMap) {
		return this.handleInfixOperation(subtractNode, variableMap, new evaluation.SubtractEvaluator());
	}
	visitMultiplyNode(multiplyNode, variableMap) {
		return this.handleInfixOperation(multiplyNode, variableMap, new evaluation.MultiplyEvaluator());
	}
	visitDivideNode(divideNode, variableMap) {
		return this.handleInfixOperation(divideNode, variableMap, new evaluation.DivideEvaluator());
	}
	visitGreaterNode(greaterNode, variableMap) {
		return this.handleInfixOperation(greaterNode, variableMap, new evaluation.GreaterEvaluator());
	}
	visitGreaterEqualNode(greaterEqualNode, variableMap) {
		return this.handleInfixOperation(greaterEqualNode, variableMap, new evaluation.GreaterEqualEvaluator());
	}
	visitLessNode(lessNode, variableMap) {
		return this.handleInfixOperation(lessNode, variableMap, new evaluation.LessEvaluator());
	}
	visitLessEqualNode(lessEqualNode, variableMap) {
		return this.handleInfixOperation(lessEqualNode, variableMap, new evaluation.LessEqualEvaluator());
	}
	visitEqualNode(equalNode, variableMap) {
		return this.handleInfixOperation(equalNode, variableMap, new evaluation.EqualEvaluator());
	}
	visitNotEqualNode(notEqualNode, variableMap) {
		return this.handleInfixOperation(notEqualNode, variableMap, new evaluation.NotEqualEvaluator());
	}
	visitAndNode(andNode, variableMap) {
		return this.handleInfixOperation(andNode, variableMap, new evaluation.AndEvaluator());
	}
	visitOrNode(orNode, variableMap) {
		return this.handleInfixOperation(orNode, variableMap, new evaluation.OrEvaluator());
	}
	visitLiteralNode(literalNode, variableMap) {
		return literalNode.value;
	}
	visitIdentifierNode(identifierNode, variableMap) {
		let name = identifierNode.name;

		return variableMap.get(name).getValue();
	}
}

class Widget extends Observable {
	getValue() {
		throw new Error("Override in subclasses");
	}
	setValue() {
		throw new Error("Override in subclasses");
	}
}

class InputWidget extends Widget {
	constructor(inputElement) {
		super();
		this.inputElement = inputElement;
		this.inputElement.onchange = () => {
			this.notifyListeners();
		};
	}
	static renderInputElement(elementFactory, containerElement, attributes) {
		let inputElement = elementFactory.createElement('input');
		_.forEach(attributes, function (value, key) {
			inputElement.setAttribute(key, value);
		});
		containerElement.appendChild(inputElement);
		return inputElement;
	}
}

class StringInputWidget extends InputWidget {
	getValue() {
		return new values.StringValue(this.inputElement.value);
	}
	setValue(value) {
		this.inputElement.value = value.toString();
	}
	static render(elementFactory, containerElement) {
		return new StringInputWidget(InputWidget.renderInputElement(elementFactory, containerElement, {
			'type': 'text'
		}));
	}
}

class BooleanCheckboxWidget extends InputWidget {
	getValue() {
		return new values.BooleanValue(this.inputElement.checked);
	}
	setValue(value) {
		this.inputElement.checked = value.equals(new values.BooleanValue(true));
	}
	static render(elementFactory, containerElement) {
		return new BooleanCheckboxWidget(InputWidget.renderInputElement(elementFactory, containerElement, {
			'type': 'checkbox'
		}));
	}
}

class IntegerInputWidget extends InputWidget {
	getValue() {
		return values.IntegerValue.fromString(this.inputElement.value);
	}
	setValue(value) {
		this.inputElement.value = value.toString();
	}
	static render(elementFactory, containerElement) {
		return new IntegerInputWidget(InputWidget.renderInputElement(elementFactory, containerElement, {
			'type': 'number',
			'step': 1
		}));
	}
}

class WidgetFactory extends types.TypeReceiver {
	constructor(elementFactory) {
		super();
		this.elementFactory = elementFactory;
	}
	render(type, containerElement) {
		return type.dispatch(this, containerElement);
	}
	receiveBoolean(type, containerElement) {
		return BooleanCheckboxWidget.render(this.elementFactory, containerElement);
	}
	receiveString(type, containerElement) {
		return StringInputWidget.render(this.elementFactory, containerElement);
	}
	receiveInteger(type, containerElement) {
		return IntegerInputWidget.render(this.elementFactory, containerElement);
	}
	receiveFloat() {
		throw new Error("TODO");
	}
	receiveMoney() {
		throw new Error("TODO");
	}
}

export class QuestionCollector extends ast.RecursingVisitor {
	collect(node, questionCollection) {
		node.accept(this, new ast.LiteralNode(null, new types.BooleanType(), new values.BooleanValue(true)), questionCollection);
	}
	visitIfNode(ifNode, condition, questionCollection) {
		ifNode.thenBlock.accept(this, new ast.AndNode(null, condition, ifNode.condition), questionCollection);
	}
	visitIfElseNode(ifElseNode, condition, questionCollection) {
		this.visitIfNode(ifElseNode, condition, questionCollection);
		ifElseNode.elseBlock.accept(this, new ast.AndNode(null, condition, new ast.NotNode(null, ifElseNode.condition)), questionCollection);
	}
	visitQuestionNode(questionNode, condition, questionCollection) {
		questionCollection.addQuestion(questionNode, condition);
	}
}

export class WidgetStatusBinder {
	constructor(containerElement, wrappedWidget, variable, enabled) {
		this.containerElement = containerElement;
		this._wrappedWidget = wrappedWidget;
		this._variable = variable;
		this._enabled = enabled;

		this._wrappedWidget.listen(() => {
			this.updateVariable();
		});
		this._variable.listen(() => {
			this.updateWidgetValue();
		});
		this.updateWidgetValue();
	}
	updateVariable() {
		if (this._enabled === true) {
			this._variable.setValue(this._wrappedWidget.getValue());
		}
	}
	updateWidgetValue() {
		if (this._enabled === true) {
			this._wrappedWidget.setValue(this._variable.getValue());
		}
	}
	setEnabled(enabled) {
		if (enabled !== this._enabled) {
			this._enabled = enabled;
			WidgetStatusBinder.setElementEnabled(this.containerElement, enabled);
			this.updateVariable();
		}
	}
	setValue(value) {
		if (this._enabled === true) {
			this._variable.setValue(value);
		}
	}
	static setElementEnabled(element, enabled) {
		if (enabled === true) {
			element.classList.remove('disabled');
		} else {
			element.classList.add('disabled');
		}
	}
	static render(elementFactory, description, type, variable, enabled, containerElement, widgetFactory) {
		let questionContainer = elementFactory.createElement('div'),
			labelElement = elementFactory.createElement('label'),
			wrappedWidget,
			widgetWrapper;

		labelElement.textContent = description;
		questionContainer.appendChild(labelElement);
		questionContainer.classList.add('question');
		WidgetStatusBinder.setElementEnabled(questionContainer, enabled);
		wrappedWidget = widgetFactory.render(type, questionContainer);
		widgetWrapper = new WidgetStatusBinder(questionContainer, wrappedWidget, variable, enabled);
		containerElement.appendChild(questionContainer);
		return widgetWrapper;
	}
}

class ExprBinder extends ast.NodeVisitor {
	listen(expr, listener, variableMap) {
		return expr.accept(this, listener, variableMap, []);
	}
	visitUnaryPrefixNode(unaryPrefixNode, listener, variableMap, boundVariables) {
		unaryPrefixNode.operand.accept(this, listener, variableMap, boundVariables);
	}
	visitInfixNode(infixNode, listener, variableMap, boundVariables) {
		infixNode.leftOperand.accept(this, listener, variableMap, boundVariables);
		infixNode.rightOperand.accept(this, listener, variableMap, boundVariables);
	}
	visitIdentifierNode(identifierNode, listener, variableMap, boundVariables) {
		let name = identifierNode.name;

		if (!boundVariables.includes(name)) {
			variableMap.get(name).listen(listener);
			boundVariables.push(name);
		}
	}
}

export class QuestionRenderer extends ast.NodeVisitor {
	constructor(elementFactory, exprEvaluator, exprBinder, variableMap) {
		super();
		this.elementFactory = elementFactory;
		this.exprEvaluator = exprEvaluator;
		this.exprBinder = exprBinder;
		this.variableMap = variableMap;
	}
	renderQuestion(questionNode, condition, containerElement, widgetFactory) {
		questionNode.accept(this, condition, containerElement, widgetFactory);
	}
	isTrue(condition) {
		return this.exprEvaluator.evaluate(condition, this.variableMap).equals(new values.BooleanValue(true));
	}
	visitQuestionNode(questionNode, condition, containerElement, widgetFactory) {
		let widgetBinder = WidgetStatusBinder.render(this.elementFactory, questionNode.description, questionNode.type, this.variableMap.get(questionNode.name), this.isTrue(condition), containerElement, widgetFactory);

		this.exprBinder.listen(condition, () => {
			widgetBinder.setEnabled(this.isTrue(condition));
		}, this.variableMap);
		return widgetBinder;
	}
	visitExprQuestionNode(exprQuestionNode, condition, containerElement, widgetFactory) {
		let widgetBinder = this.visitQuestionNode(exprQuestionNode, condition, containerElement, widgetFactory),
			expr = exprQuestionNode.expr;

		this.exprBinder.listen(expr, () => {
			widgetBinder.setValue(this.exprEvaluator.evaluate(expr, this.variableMap));
		}, this.variableMap);
	}
}

export class DirectRenderingQuestionCollection {
	constructor(questionRenderer, widgetFactory, containerElement) {
		this.questionRenderer = questionRenderer;
		this.widgetFactory = widgetFactory;
		this.containerElement = containerElement;
	}
	addQuestion(questionNode, condition) {
		this.questionRenderer.renderQuestion(questionNode, condition, this.containerElement, this.widgetFactory);
	}
}

export class Renderer {
	constructor(elementFactory) {
		this.elementFactory = elementFactory;
		this.questionCollector = new QuestionCollector();
	}
	render(node, containerElement) {
		let questionRenderer = new QuestionRenderer(this.elementFactory, new ExprEvaluator(), new ExprBinder(), new VariableMap()),
			widgetFactory = new WidgetFactory(this.elementFactory),
			directRenderingQuestionCollection = new DirectRenderingQuestionCollection(questionRenderer, widgetFactory, containerElement);

		this.questionCollector.collect(node, directRenderingQuestionCollection);
	}
}