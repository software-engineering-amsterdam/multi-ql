export class Node {
	constructor(line) {
		this.line = line;
	}
	accept() {
		throw new Error("Override in subclasses");
	}
}

export class StylesheetNode extends Node {
	constructor(line, name, block) {
		super(line);
		this.name = name;
		this.block = block;
	}
	accept(visitor, ...args) {
		return visitor.visitStylesheetNode(this, ...args);
	}
}

export class StylesheetBlockNode extends Node {
	constructor(line, statements) {
		super(line);
		this.statements = statements;
	}
	accept(visitor, ...args) {
		return visitor.visitStylesheetBlockNode(this, ...args);
	}
}

export class PageNode extends Node {
	constructor(line, name, block) {
		super(line);
		this.name = name;
		this.block = block;
	}
	accept(visitor, ...args) {
		return visitor.visitPageNode(this, ...args);
	}
}

export class PageBlockNode extends Node {
	constructor(line, statements) {
		super(line);
		this.statements = statements;
	}
	accept(visitor, ...args) {
		return visitor.visitPageBlockNode(this, ...args);
	}
}

export class SectionNode extends Node {
	constructor(line, description, block) {
		super(line);
		this.description = description;
		this.block = block;
	}
	accept(visitor, ...args) {
		return visitor.visitSectionNode(this, ...args);
	}
}

export class QuestionNode extends Node {
	constructor(line, name) {
		super(line);
		this.name = name;
	}
	accept(visitor, ...args) {
		return visitor.visitQuestionNode(this, ...args);
	}
}

export class ConfiguredQuestionNode extends QuestionNode {
	constructor(line, name, widgetConfiguration) {
		super(line, name);
		this.widgetConfiguration = widgetConfiguration;
	}
	accept(visitor, ...args) {
		return visitor.visitConfiguredQuestionNode(this, ...args);
	}
}

export class TypeDefaultNode extends Node {
	constructor(line, type, block) {
		super(line);
		this.type = type;
		this.block = block;
	}
	accept(visitor, ...args) {
		return visitor.visitTypeDefaultNode(this, ...args);
	}
}

export class WidgetConfigurationNode extends Node {
	constructor(line, widgetArgs, widgetType) {
		super(line);
		this.widgetArgs = widgetArgs;
		this.widgetType = widgetType;
	}
	accept(visitor, ...args) {
		return visitor.visitWidgetConfiguration(this, ...args);
	}
}

export class WidgetArgNode extends Node {
	constructor(line, key, value) {
		super(line);
		this.key = key;
		this.value = value;
	}
	accept(visitor, ...args) {
		return visitor.visitWidgetArgNode(this, ...args);
	}
}

export class WidgetTypeNode extends Node {}

export class SliderWidgetNode extends WidgetTypeNode {
	accept(visitor, ...args) {
		return visitor.visitSliderWidgetNode(this, ...args);
	}
}

export class TextWidgetNode extends WidgetTypeNode {
	accept(visitor, ...args) {
		return visitor.visitTextWidgetNode(this, ...args);
	}
}

export class RadioWidgetNode extends WidgetTypeNode {
	constructor(line, options) {
		super(line);
		this.options = options;
	}
	accept(visitor, ...args) {
		return visitor.visitRadioWidgetNode(this, ...args);
	}
}

export class LiteralNode extends Node {
	constructor(line, type, value) {
		super(line);
		this.type = type;
		this.value = value;
	}
	accept(visitor, ...args) {
		return visitor.visitLiteralNode(this, ...args);
	}
}

export class NodeVisitor {
	visitNode() {}
	visitStylesheetNode(stylesheetNode, ...args) {
		return this.visitNode(stylesheetNode, ...args);
	}
	visitStylesheetBlockNode(stylesheetBlockNode, ...args) {
		return this.visitNode(stylesheetBlockNode, ...args);
	}
	visitPageNode(pageNode, ...args) {
		return this.visitNode(pageNode, ...args);
	}
	visitPageBlockNode(pageBlockNode, ...args) {
		return this.visitNode(pageBlockNode, ...args);
	}
	visitSectionNode(sectionNode, ...args) {
		return this.visitNode(sectionNode, ...args);
	}
	visitQuestionNode(questionNode, ...args) {
		return this.visitNode(questionNode, ...args);
	}
	visitConfiguredQuestionNode(configuredQuestionNode, ...args) {
		return this.visitQuestionNode(configuredQuestionNode, ...args);
	}
	visitTypeDefaultNode(typeDefaultNode, ...args) {
		return this.visitNode(typeDefaultNode, ...args);
	}
	visitWidgetArgNode(widgetArgNode, ...args) {
		return this.visitNode(widgetArgNode, ...args);
	}
	visitWidgetTypeNode(widgetNode, ...args) {
		return this.visitNode(widgetNode, ...args);
	}
	visitSliderWidgetNode(sliderWidgetNode, ...args) {
		return this.visitWidgetTypeNode(sliderWidgetNode, ...args);
	}
	visitTextWidgetNode(textWidgetNode, ...args) {
		return this.visitWidgetTypeNode(textWidgetNode, ...args);
	}
	visitRadioWidgetNode(radioWidgetNode, ...args) {
		return this.visitWidgetTypeNode(radioWidgetNode, ...args);
	}
}

export class RecursingVisitor extends NodeVisitor {
	visitStylesheetNode(stylesheetNode, ...args) {
		stylesheetNode.block.accept(this, ...args);
	}
	visitStylesheetBlockNode(stylesheetBlockNode, ...args) {
		for (let statement of stylesheetBlockNode.statements) {
			statement.accept(this, ...args);
		}
	}
	visitPageNode(pageNode, ...args) {
		pageNode.block.accept(this, ...args);
	}
	visitPageBlockNode(pageBlockNode, ...args) {
		for (let statement of pageBlockNode.statements) {
			statement.accept(this, ...args);
		}
	}
	visitSectionNode(sectionNode, ...args) {
		sectionNode.block.accept(this, ...args);
	}
}