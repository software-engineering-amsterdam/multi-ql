import * as ast from 'src/ast';
import { Scope } from 'src/scope';
import { ExprObservableFactory } from 'src/expr_observable';

class IfUpdater {
	constructor(ifContainerElement, exprObservable, ifNode, renderingVisitor, scope) {
		this.ifContainerElement = ifContainerElement;
		this.exprObservable = exprObservable;
		this.ifNode = ifNode;
		this.renderingVisitor = renderingVisitor;
		this.scope = scope;
	}
	notify() {
		while (this.ifContainerElement.firstChild !== null) {
			this.ifContainerElement.removeChild(this.ifContainerElement.firstChild);
		}
		if (this.exprObservable.getValue() === true) {
			this.ifNode.thenBlock.accept(this.renderingVisitor, this.ifContainerElement, this.scope);
		} else if (this.ifNode.elseBlock !== null) {
			this.ifNode.elseBlock.accept(this.renderingVisitor, this.ifContainerElement, this.scope);
		}
	}
}

class QuestionRenderer {
	constructor(elementFactory, widgetFactory) {
		this.elementFactory = elementFactory;
		this.widgetFactory = widgetFactory;
	}
	render(questionNode, containerElement, scope) {
		let questionContainerElement = this.elementFactory.createElement('div'),
			questionlabelElement = this.elementFactory.createElement('label'),
			widget;

		questionContainerElement.setAttribute('class', 'question');
		questionlabelElement.textContent = questionNode.description;
		questionContainerElement.appendChild(questionlabelElement);

		widget = this.renderWidget(questionNode, questionContainerElement, scope);

		scope.set(questionNode.name, widget);
		containerElement.appendChild(questionContainerElement);
	}
	/** @protected */
	renderWidget() {
		throw new Error("Override in subclasses");
	}
}

class InputQuestionRenderer extends QuestionRenderer {
	constructor(elementFactory, widgetFactory) {
		super(elementFactory, widgetFactory);
	}
	renderWidget(inputQuestionNode, questionContainerElement, scope) {
		return this.widgetFactory.renderWidget(inputQuestionNode.type, questionContainerElement, false);
	}
}

class ExprQuestionUpdater {
	constructor(widget, exprObservable) {
		this.widget = widget;
		this.exprObservable = exprObservable;
	}
	notify() {
		this.widget.setValue(this.exprObservable.getValue());
	}
}

class ExprQuestionRenderer extends QuestionRenderer {
	constructor(elementFactory, widgetFactory, exprObservableFactory) {
		super(elementFactory, widgetFactory);
		this.exprObservableFactory = exprObservableFactory;
	}
	renderWidget(exprQuestionNode, questionContainerElement, scope) {
		let exprObservable = this.exprObservableFactory.createExprObservable(exprQuestionNode.expr, scope),
			widget = this.widgetFactory.renderWidget(exprObservable.getType(), questionContainerElement, true),
			updater = new ExprQuestionUpdater(widget, exprObservable);

		exprObservable.registerObserver(updater);
		updater.notify();
		return widget;
	}
}

class RenderingVisitor extends ast.NodeVisitor {
	constructor(elementFactory, widgetFactory, exprObservableFactory) {
		super();
		this.elementFactory = elementFactory;
		this.widgetFactory = widgetFactory;
		this.exprObservableFactory = exprObservableFactory;
		this.inputQuestionRenderer = new InputQuestionRenderer(elementFactory, widgetFactory);
		this.exprQuestionRenderer = new ExprQuestionRenderer(elementFactory, widgetFactory, exprObservableFactory);
	}
	visitFormNode(formNode, containerElement, scope) {
		let descriptionElement = this.elementFactory.createElement('h2');

		descriptionElement.textContent = formNode.description;
		formNode.block.accept(this, containerElement, scope);
		containerElement.appendChild(descriptionElement);
	}
	visitBlockNode(blockNode, containerElement, scope) {
		let blockScope = new Scope(scope);

		for (let statement of blockNode.statements) {
			statement.accept(this, containerElement, blockScope);
		}
	}
	visitIfNode(ifNode, containerElement, scope) {
		let ifContainerElement = this.elementFactory.createElement('div'),
			exprObservable = this.exprObservableFactory.createExprObservable(ifNode.condition),
			ifUpdater = new IfUpdater(ifContainerElement, exprObservable, ifNode, this, scope);

		exprObservable.registerObserver(ifUpdater);
		ifUpdater.notify();
		containerElement.appendChild(ifContainerElement);
	}
	visitInputQuestionNode(inputQuestionNode, containerElement, scope) {
		this.inputQuestionRenderer.render(inputQuestionNode, containerElement, scope);
	}
	visitExprQuestionNode(exprQuestionNode, containerElement, scope) {
		this.exprQuestionRenderer.render(exprQuestionNode, containerElement, scope);
	}
}

export class Renderer {
	constructor(elementFactory, widgetFactory, exprObservableFactory) {
		this.elementFactory = elementFactory;
		this.widgetFactory = widgetFactory;
		this.exprObservableFactory = exprObservableFactory;
	}
	render(node, containerElement) {
		node.accept(new RenderingVisitor(this.elementFactory, this.widgetFactory, this.exprObservableFactory), containerElement, new Scope());
	}
}