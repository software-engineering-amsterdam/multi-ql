import * as _ from 'lodash';
import * as ast from 'src/ql/ast';
import * as types from 'src/ql/types';
import * as values from 'src/ql/values';
import * as evaluation from 'src/ql/evaluation';
import { Observable } from 'src/ql/observable';
import { WidgetFactory } from 'src/ql/widgets';
import { ExprEvaluator } from 'src/ql/expr_evaluation';

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
	constructor(elementFactory) {
		super();
		this.elementFactory = elementFactory;
		this.exprEvaluator = new ExprEvaluator();
		this.exprBinder = new ExprBinder();
		this.variableMap = new VariableMap();
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
		let questionRenderer = new QuestionRenderer(this.elementFactory),
			widgetFactory = new WidgetFactory(this.elementFactory),
			directRenderingQuestionCollection = new DirectRenderingQuestionCollection(questionRenderer, widgetFactory, containerElement);

		this.questionCollector.collect(node, directRenderingQuestionCollection);
	}
}