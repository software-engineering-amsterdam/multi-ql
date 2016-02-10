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
		return visitor.visitUnaryPrefixNode(this);
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
		return visitor.visitInfixNode(this);
	}
}

export class LiteralNode extends Node {
	constructor(line, value, type) {
		super(line);
		this.value = value;
		this.type = type;
	}
	accept (visitor) {
		return visitor.visitLiteralNode(this);
	}
}

export class IdentifierNode extends Node {
	constructor(line, name) {
		super(line);
		this.name = name;
	}
	accept (visitor) {
		return visitor.visitIdentifierNode(this);
	}
}

/**
 * Node Visitor which by default simply recurses over all nodes
 */
export class NodeVisitor {
	visitFormNode (formNode) {
		formNode.block.accept(this);
	}
	visitBlockNode (blockNode) {
		for (let statement of blockNode.statements) {
			statement.accept(this);
		}
	}
	visitIfNode (ifNode) {
		ifNode.condition.accept(this);
		ifNode.thenBlock.accept(this);
		if (ifNode.elseBlock !== null) {
			ifNode.elseBlock.accept(this);
		}
	}
	visitQuestionNode (questionNode) {}
	visitUnaryPrefixNode (unaryPrefixNode) {
		unaryPrefixNode.operand.accept(this);
	}
	visitInfixNode (infixNode) {
		infixNode.leftOperand.accept(this);
		infixNode.rightOperand.accept(this);
	}
	visitLiteralNode (literalNode) {}
	visitIdentifierNode (literalNode) {}
}
