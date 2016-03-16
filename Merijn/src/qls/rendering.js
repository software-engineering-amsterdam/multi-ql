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
	collect(node, typeDefaults) {
		return node.accept(this, typeDefaults);
	}
	visitSectionNode() {}   // we only want type default declarations for this page or section, so do not recurse
	visitTypeDefaultNode(typeDefaultNode, typeDefaults) {
		typeDefaults.add(typeDefaultNode);
	}
}

class TypeDefaultsScopedWidgetRenderer {
	constructor(wrappedWidgetFactory, typeDefaults) {
		this.wrappedWidgetFactory = wrappedWidgetFactory;
		this.typeDefaults = typeDefaults;
	}
	render(type, containerElement, disabled) {
		//if (this.typeDefaults.hasTypeDefault(type)) {
		//	// create widget based on type default
		//}
		return this.wrappedWidgetFactory.render(type, containerElement, disabled);
	}
}

class StyleScopedWidgetRenderer {
	constructor() {

	}
}

class ScopedWidgetRendererFactory {
	createTypeDefaultsScopedWidgetRenderer(widgetRenderer, typeDefaults) {
		return new TypeDefaultsScopedWidgetRenderer(widgetRenderer, typeDefaults);
	}
	createStyleScopedWidgetRenderer(widgetRenderer, styleBlock) {

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
	renderQuestions(name, containerElement, widgetRenderer) {
		if (_.has(this._questions, name)) {
			_.forEach(this._questions[name], (question) => {
				this.questionRenderer.renderQuestion(question.node, question.condition, containerElement, widgetRenderer);
			});
		}
	}
}

export class Renderer extends RecursingVisitor {
	constructor(elementFactory, widgetRenderer) {
		super();
		this.elementFactory = elementFactory;
		this.widgetRenderer = widgetRenderer;
		this.questionConditionComputer = new QuestionConditionComputer();
		this.scopedWidgetRendererFactory = new ScopedWidgetRendererFactory();
		this.typeDefaultsCollector = new TypeDefaultsCollector();
	}
	render(qlNode, qlsNode, containerElement) {
		let manualRenderingStrategy = new ManualRenderingStrategy(this.elementFactory, new QuestionRenderer(this.elementFactory));

		this.questionConditionComputer.computeQuestionConditions(qlNode, manualRenderingStrategy);
		return qlsNode.accept(this, containerElement, manualRenderingStrategy, this.widgetRenderer);
	}
	createScopedWidgetFactoryFromPageBlock(pageBlockNode, widgetRenderer) {
		return this.scopedWidgetRendererFactory.createTypeDefaultsScopedWidgetRenderer(widgetRenderer, this.typeDefaultsCollector.collect(pageBlockNode, new TypeDefaults()));
	}
	visitPageNode(pageNode, containerElement, manualRenderingStrategy, widgetRenderer) {
		let pageContainer = this.elementFactory.createElement('fieldset'),
			legendElement = this.elementFactory.createElement('legend'),
			scopedWidgetFactory = this.createScopedWidgetFactoryFromPageBlock(pageNode.block, widgetRenderer);

		pageContainer.classList.add('page');
		legendElement.textContent = 'page: ' + pageNode.name;
		pageContainer.appendChild(legendElement);
		pageNode.block.accept(this, pageContainer, manualRenderingStrategy, scopedWidgetFactory);
		containerElement.appendChild(pageContainer);
	}
	visitSectionNode(sectionNode, containerElement, manualRenderingStrategy, widgetRenderer) {
		let sectionContainer = this.elementFactory.createElement('fieldset'),
			legendElement = this.elementFactory.createElement('legend'),
			scopedWidgetFactory = this.createScopedWidgetFactoryFromPageBlock(sectionNode.block, widgetRenderer);

		sectionContainer.classList.add('section');
		legendElement.textContent = 'section: ' + sectionNode.description;
		sectionContainer.appendChild(legendElement);
		sectionNode.block.accept(this, sectionContainer, manualRenderingStrategy, scopedWidgetFactory);
		containerElement.appendChild(sectionContainer);
	}
	visitQuestionNode(questionNode, containerElement, manualRenderingStrategy, widgetRenderer) {
		manualRenderingStrategy.renderQuestions(questionNode.name, containerElement, widgetRenderer);
	}
	visitConfiguredQuestionNode(configuredQuestionNode, containerElement, manualRenderingStrategy, widgetRenderer) {
		//let scopedWidgetRenderer = this.scopedWidgetRendererFactory.createStyleScopedWidgetRenderer(configuredQuestionNode.widgetConfiguration);
		manualRenderingStrategy.renderQuestions(configuredQuestionNode.name, containerElement, widgetRenderer);
	}
}