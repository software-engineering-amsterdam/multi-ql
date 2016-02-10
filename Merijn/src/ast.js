export var TYPE_BOOLEAN = "boolean";
export var TYPE_STRING = "string";
export var TYPE_INTEGER = "integer";
export var TYPE_FLOAT = "float";
export var TYPE_MONEY = "money";

export class Node {
	constructor (line) {
		this.line = line;
	}
	accept() {
		throw new Error("Override this in subclasses");
	}
}

export class FormNode extends Node {
	constructor(line, block) {
		super(line);
		this.block = block;
	}
	accept (visitor) {
		return visitor.visitFormNode(this);
	}
}

export class BlockNode extends Node {
	constructor(line, statements) {
		super(line);
		this.statements = statements;
	}
	accept (visitor) {
		return visitor.visitBlockNode(this);
	}
}

export class IfNode extends Node {
	constructor(line, condition, thenBlock, elseBlock) {
		super(line);
		this.condition = condition;
		this.thenBlock = thenBlock;
		this.elseBlock = elseBlock; // block | null
	}
	accept (visitor) {
		return visitor.visitIfNode(this);
	}
}

export class QuestionNode extends Node {
	constructor(line, description, name, type) {
		super(line);
		this.description = description;
		this.name = name;
		this.type = type;
	}
	accept (visitor) {
		return visitor.visitQuestionNode(this);
	}
}

export class UnaryPrefixNode extends Node {
	constructor(line, operation, operand) {
		super(line);
		this.operation = operation;
		this.operand = operand;
	}
	accept (visitor) {
		return visitor.visitUnaryPrefixNode(this.operation, this.operand);
	}
}

export class InfixNode extends Node {
	constructor(line, leftOperand, operation, rightOperand) {
		super(line);
		this.leftOperand = leftOperand;
		this.operation = operation;
		this.rightOperand = rightOperand;
	}
	accept (visitor) {
		return visitor.visitInfixNode(this.leftOperand, this.operation, this.rightOperand);
	}
}

export class LiteralNode extends Node {
	constructor(line, value, type) {
		super(line);
		this.value = value;
		this.type = type;
	}
	accept (visitor) {
		return visitor.visitLiteralNode(this.value, this.type);
	}
}

export class NodeVisitor {
	visitFormNode (formNode) {}
	visitBlockNode (blockNode) {}
	visitIfNode (ifNode) {}
	visitQuestionNode (questionNode) {}
	visitUnaryPrefixNode (unaryPrefixNode) {}
	visitInfixNode (infixNode) {}
	visitLiteralNode (literalNode) {}
}
