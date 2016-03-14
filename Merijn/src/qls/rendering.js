import * as _ from 'lodash';
import { NodeVisitor, RecursingVisitor } from 'src/qls/ast';
import { QuestionConditionComputer, QuestionRenderer, WidgetFactory } from 'src/ql/rendering';

class TypeDefaults {
	constructor() {
		this._typeDefaultNodes = [];
	}
	add(typeDefaultNode) {
		this._typeDefaultNodes.push(typeDefaultNode);
	}
	hasTypeDefault(type) {
		for (let typeDefaultNode of this._typeDefaultNodes) {
			if (typeDefaultNode.type.equals(type)) {
				return true;
			}
		}
		return false;
	}
	getTypeDefault(type) {
		for (let typeDefaultNode of this._typeDefaultNodes) {
			if (typeDefaultNode.type.equals(type)) {
				return typeDefaultNode;
			}
		}
	}
}

class TypeDefaultsCollector extends RecursingVisitor {
	visitSectionNode() {}   // we only want type default declarations for this page or section, so do not recurse
	visitTypeDefaultNode(typeDefaultNode, typeDefaults) {
		typeDefaults.add(typeDefaultNode);
	}
}


class TypeDefaultScopedWidgetFactory {
	constructor(wrappedWidgetFactory, typeDefaults) {
		this.wrappedWidgetFactory = wrappedWidgetFactory;
		this.typeDefaults = typeDefaults;
	}
	createWidget(type, containerElement, disabled) {
		if (this.typeDefaults.hasTypeDefault(type)) {
			// create widget based on type default
		}
		return this.wrappedWidgetFactory.createWidget(type, containerElement, disabled);
	}
}

class ScopedWidgetFactoryFactory {
	createTypeDefaultScopedWidgetFactory(widgetFactory, typeDefaults) {
		return new TypeDefaultScopedWidgetFactory(widgetFactory, typeDefaults);
	}
}

class ManualRenderingStrategy {
	constructor(elementFactory, questionRenderer) {
		this.elementFactory = elementFactory;
		this.questionRenderer = questionRenderer;
		this._questions = {};
	}
	handleQuestion(questionNode, condition) {
		let name = questionNode.name;

		if (!_.has(this._questions, name)) {
			this._questions[name] = [];
		}
		this._questions[name].push({
			node: questionNode,
			condition: condition
		});
	}
	renderQuestions(name, containerElement, widgetFactory) {
		if (_.has(this._questions, name)) {
			_.forEach(this._questions[name], (question) => {
				this.questionRenderer.renderQuestion(question.node, question.condition, containerElement, widgetFactory);
			});
		}
	}
}

export class Renderer extends RecursingVisitor {
	constructor(elementFactory, widgetFactory) {
		super();
		this.elementFactory = elementFactory;
		this.widgetFactory = widgetFactory;
		this.questionConditionComputer = new QuestionConditionComputer();
		this.scopedWidgetFactoryFactory = new ScopedWidgetFactoryFactory();
		this.typeDefaultsCollector = new TypeDefaultsCollector();
	}
	render(qlNode, qlsNode, containerElement) {
		let manualRenderingStrategy = new ManualRenderingStrategy(this.elementFactory, new QuestionRenderer(this.elementFactory));

		this.questionConditionComputer.computeQuestionConditions(qlNode, manualRenderingStrategy);
		return qlsNode.accept(this, containerElement, manualRenderingStrategy, this.widgetFactory);
	}
	createScopedWidgetFactory(pageBlockNode, widgetFactory) {
		//return this.scopedWidgetFactoryFactory.createTypeDefaultScopedWidgetFactory(widgetFactory, this.typeDefaultsCollector.collect(pageBlockNode, new TypeDefaults()));
	}
	visitPageNode(pageNode, containerElement, manualRenderingStrategy, widgetFactory) {
		let pageContainer = this.elementFactory.createElement('fieldset'),
			legendElement = this.elementFactory.createElement('legend'),
			scopedWidgetFactory = this.createScopedWidgetFactory(pageNode.block, widgetFactory);

		pageContainer.classList.add('page');
		legendElement.textContent = 'page: ' + pageNode.name;
		pageContainer.appendChild(legendElement);
		pageNode.block.accept(this, pageContainer, manualRenderingStrategy, widgetFactory);
		containerElement.appendChild(pageContainer);
	}
	visitSectionNode(sectionNode, containerElement, manualRenderingStrategy, widgetFactory) {
		let sectionContainer = this.elementFactory.createElement('fieldset'),
			legendElement = this.elementFactory.createElement('legend'),
			scopedWidgetFactory = this.createScopedWidgetFactory(sectionNode.block, widgetFactory);

		sectionContainer.classList.add('section');
		legendElement.textContent = 'section: ' + sectionNode.description;
		sectionContainer.appendChild(legendElement);
		sectionNode.block.accept(this, sectionContainer, manualRenderingStrategy, widgetFactory);
		containerElement.appendChild(sectionContainer);
	}
	visitQuestionNode(questionNode, containerElement, manualRenderingStrategy, widgetFactory) {
		manualRenderingStrategy.renderQuestions(questionNode.name, containerElement, widgetFactory);
	}
}