import * as _ from 'lodash';
import { NodeVisitor, RecursingVisitor, AndNode, NotNode, LiteralNode} from 'src/ql/ast';
import { BooleanValue, UndefinedValue } from 'src/ql/values';
import { BooleanType } from 'src/ql/types';
import { Observable } from 'src/ql/observable';
import { WidgetFactory } from 'src/ql/widgets';
import { ExprEvaluator } from 'src/ql/expr_evaluation';

class Variable extends Observable {
	constructor() {
		super();
		this._value = new UndefinedValue();
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

export class QuestionConditionComputer extends RecursingVisitor {
	computeQuestionConditions(node, questionHandlingStrategy) {
		node.accept(this, new LiteralNode(null, new BooleanType(), new BooleanValue(true)), questionHandlingStrategy);
	}
	visitIfNode(ifNode, condition, questionHandlingStrategy) {
		ifNode.thenBlock.accept(this, new AndNode(null, condition, ifNode.condition), questionHandlingStrategy);
	}
	visitIfElseNode(ifElseNode, condition, questionHandlingStrategy) {
		this.visitIfNode(ifElseNode, condition, questionHandlingStrategy);
		ifElseNode.elseBlock.accept(this, new AndNode(null, condition, new NotNode(null, ifElseNode.condition)), questionHandlingStrategy);
	}
	visitQuestionNode(questionNode, condition, questionHandlingStrategy) {
		questionHandlingStrategy.handleQuestion(questionNode, condition);
	}
}

export class WidgetStatusBinder {
	constructor(containerElement, wrappedWidget, variable, enabled) {
		this.containerElement = containerElement;
		this._wrappedWidget = wrappedWidget;
		this._variable = variable;
		this._enabled = enabled;

		this._wrappedWidget.listen(() => {
			this.updateVariableIfEnabled();
		});
		this._variable.listen(() => {
			this.updateWidgetIfEnabled();
		});
		this.updateVariableIfEnabled();
	}
	updateVariableIfEnabled() {
		if (this._enabled === true) {
			this._variable.setValue(this._wrappedWidget.getValue());
		}
	}
	updateWidgetIfEnabled() {
		if (this._enabled === true) {
			this._wrappedWidget.setValue(this._variable.getValue());
		}
	}
	setEnabled(enabled) {
		if (enabled !== this._enabled) {
			this._enabled = enabled;
			WidgetStatusBinder.setElementEnabled(this.containerElement, enabled);
			this.updateVariableIfEnabled();
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

class ExprBinder extends NodeVisitor {
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

export class QuestionRenderer extends NodeVisitor {
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
		return this.exprEvaluator.evaluate(condition, this.variableMap).equals(new BooleanValue(true));
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

export class DirectRenderingStrategy {
	constructor(questionRenderer, widgetFactory, containerElement) {
		this.questionRenderer = questionRenderer;
		this.widgetFactory = widgetFactory;
		this.containerElement = containerElement;
	}
	handleQuestion(questionNode, condition) {
		this.questionRenderer.renderQuestion(questionNode, condition, this.containerElement, this.widgetFactory);
	}
}

export class Renderer {
	constructor(elementFactory) {
		this.elementFactory = elementFactory;
		this.questionConditionComputer = new QuestionConditionComputer();
	}
	render(node, containerElement) {
		let questionRenderer = new QuestionRenderer(this.elementFactory),
			widgetFactory = new WidgetFactory(this.elementFactory),
			directRenderingQuestionCollection = new DirectRenderingStrategy(questionRenderer, widgetFactory, containerElement);

		this.questionConditionComputer.computeQuestionConditions(node, directRenderingQuestionCollection);
	}
}