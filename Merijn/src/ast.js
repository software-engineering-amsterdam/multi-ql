export var TYPE_INTEGER = 0;
export var TYPE_FLOAT = 1;
export var TYPE_MONEY = 2;
export var TYPE_STRING = 3;

export class Node {
	accept() {
		throw new Error("Override this in subclasses");
	}
}

export class Form extends Node {
	constructor(block) {
		super();
		this.block = block;
	}
	accept (visitor) {
		visitor.visitForm(this.block);
	}
}

export class Block extends Node {
	constructor(statements) {
		super();
		this.statements = statements;
	}
	accept (visitor) {
		visitor.visitBlock(this.statements);
	}
}

export class If extends Node {
	constructor(condition, thenBlock, elseBlock) {
		super();
		this.condition = condition;
		this.thenBlock = thenBlock;
		this.elseBlock = elseBlock; // block | null
	}
	accept (visitor) {
		visitor.visitIf(this.condition, this.thenBlock, this.elseBlock);
	}
}

export class Question extends Node {
	constructor(description, name, type) {
		super();
		this.description = description;
		this.name = name;
		this.type = type;
	}
	accept (visitor) {
		visitor.visitQuestion(this.description, this.name, this.type);
	}
}

export class Unary extends Node {
	constructor(operation, operand) {
		super();
		this.operation = operation;
		this.operand = operand;
	}
	accept (visitor) {
		visitor.visitUnary(this.operation, this.operand);
	}
}

export class Infix extends Node {
	constructor(left_operand, operation, right_operand) {
		super();
		this.left_operand = left_operand;
		this.operation = operation;
		this.right_operand = right_operand;
	}
	accept(visitor) {
		visitor.visitInfix(this.left_operand, this.operation, this.right_operand);
	}
}

export class Literal extends Node {
	constructor(value, type) {
		super();
		this.value = value;
		this.type = type;
	}
	accept (visitor) {
		visitor.visitLiteral(this.value, this.type);
	}
}

export class Visitor {
	visitForm (block) {}
	visitBlock (statements) {}
	visitIf (condition, thenBlock, elseBlock) {}
	visitQuestion (description, name, type) {}
	visitUnary (operation, operand) {}
	visitInfix (left_operand, operation, right_operand) {}
	visitLiteral (value, type) {}
}