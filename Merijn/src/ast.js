export class Node {
	constructor (line) {
		this.line = line;
	}
	accept() {
		throw new Error("Override in subclasses");
	}
}

export class FormNode extends Node {
	constructor(line, description, block) {
		super(line);
		this.description = description;
		this.block = block;
	}
	accept (visitor, ...args) {
		return visitor.visitFormNode(this, ...args);
	}
}

export class BlockNode extends Node {
	constructor(line, statements) {
		super(line);
		this.statements = statements;
	}
	accept (visitor, ...args) {
		return visitor.visitBlockNode(this, ...args);
	}
}

export class IfNode extends Node {
	constructor(line, condition, thenBlock, elseBlock) {
		super(line);
		this.condition = condition;
		this.thenBlock = thenBlock;
		this.elseBlock = elseBlock; // block | null
	}
	accept (visitor, ...args) {
		return visitor.visitIfNode(this, ...args);
	}
}

export class QuestionNode extends Node {
	constructor(line, description, name) {
		super(line);
		this.line = line;
		this.description = description;
		this.name = name;
	}
}

export class InputQuestionNode extends QuestionNode {
	constructor(line, description, name, type) {
		super(line, description, name);
		this.type = type;
	}
	accept (visitor, ...args) {
		return visitor.visitInputQuestionNode(this, ...args);
	}
}

export class ExprQuestionNode extends QuestionNode {
	constructor(line, description, name, expr) {
		super(line, description, name);
		this.expr = expr;
	}
	accept (visitor, ...args) {
		return visitor.visitExprQuestionNode(this, ...args);
	}
}

export class OperationNode extends Node {
	toString() {
		throw new Error("Override in subclasses");
	}
}

export class UnaryPrefixNode extends OperationNode {
	constructor(line, operand) {
		super(line);
		this.operand = operand;
	}
}

export class NegationNode extends UnaryPrefixNode {
	accept (visitor, ...args) {
		return visitor.visitNegationNode(this, ...args);
	}
	toString() {
		return '-';
	}
}

export class NotNode extends UnaryPrefixNode {
	accept (visitor, ...args) {
		return visitor.visitNotNode(this, ...args);
	}
	toString() {
		return '!';
	}
}

export class InfixNode extends OperationNode {
	constructor(line, leftOperand, rightOperand) {
		super(line);
		this.leftOperand = leftOperand;
		this.rightOperand = rightOperand;
	}
}

export class AddNode extends InfixNode {
	accept (visitor, ...args) {
		return visitor.visitAddNode(this, ...args);
	}
	toString() {
		return '+';
	}
}

export class SubtractNode extends InfixNode {
	accept (visitor, ...args) {
		return visitor.visitSubtractNode(this, ...args);
	}
	toString() {
		return '-';
	}
}

export class MultiplyNode extends InfixNode {
	accept (visitor, ...args) {
		return visitor.visitMultiplyNode(this, ...args);
	}
	toString() {
		return '*';
	}
}

export class DivideNode extends InfixNode {
	accept (visitor, ...args) {
		return visitor.visitDivideNode(this, ...args);
	}
	toString() {
		return '/';
	}
}

export class GreaterNode extends InfixNode {
	accept (visitor, ...args) {
		return visitor.visitGreaterNode(this, ...args);
	}
	toString() {
		return '>';
	}
}

export class GreaterEqualNode extends InfixNode {
	accept (visitor, ...args) {
		return visitor.visitGreaterEqualNode(this, ...args);
	}
	toString() {
		return '>=';
	}
}

export class LessNode extends InfixNode {
	accept (visitor, ...args) {
		return visitor.visitLessNode(this, ...args);
	}
	toString() {
		return '<';
	}
}

export class LessEqualNode extends InfixNode {
	accept (visitor, ...args) {
		return visitor.visitLessEqualNode(this, ...args);
	}
	toString() {
		return '<=';
	}
}

export class EqualNode extends InfixNode {
	accept (visitor, ...args) {
		return visitor.visitEqualNode(this, ...args);
	}
	toString() {
		return '==';
	}
}

export class NotEqualNode extends InfixNode {
	accept (visitor, ...args) {
		return visitor.visitNotEqualNode(this, ...args);
	}
	toString() {
		return '!=';
	}
}

export class AndNode extends InfixNode {
	accept (visitor, ...args) {
		return visitor.visitAndNode(this, ...args);
	}
	toString() {
		return '&&';
	}
}

export class OrNode extends InfixNode {
	accept (visitor, ...args) {
		return visitor.visitOrNode(this, ...args);
	}
	toString() {
		return '||';
	}
}

export class LiteralNode extends Node {
	constructor(line, type, value) {
		super(line);
		this.type = type;
		this.value = value;
	}
}

export class IdentifierNode extends Node {
	constructor(line, name) {
		super(line);
		this.name = name;
	}
	accept (visitor, ...args) {
		return visitor.visitIdentifierNode(this, ...args);
	}
}

/**
 * Node Visitor which by default simply recurses over all nodes
 */
export class NodeVisitor {
	visitFormNode (formNode, ...args) {
		return formNode.block.accept(this, ...args);
	}
	visitBlockNode (blockNode, ...args) {
		return blockNode.statements.map((statement) => {
			return statement.accept(this, ...args);
		});
	}
	visitIfNode (ifNode, ...args) {
		return [
			ifNode.condition.accept(this, ...args),
			ifNode.thenBlock.accept(this, ...args),
			ifNode.elseBlock !== null ? ifNode.elseBlock.accept(this, ...args) : null
		];
	}

	visitInputQuestionNode () {}
	visitExprQuestionNode(exprQuestionNode, ...args) {
		return exprQuestionNode.expr.accept(this, ...args);
	}

	visitUnaryPrefixNode (unaryPrefixNode, ...args) {
		return unaryPrefixNode.operand.accept(this, ...args);
	}
	visitNegationNode (negationNode, ...args) {
		return this.visitUnaryPrefixNode(negationNode, ...args);
	}
	visitNotNode (notNode, ...args) {
		return this.visitUnaryPrefixNode(notNode, ...args);
	}

	visitInfixNode (infixNode, ...args) {
		return [
			infixNode.leftOperand.accept(this, ...args),
			infixNode.rightOperand.accept(this, ...args)
		];
	}
	visitAddNode (addNode, ...args) {
		return this.visitInfixNode(addNode, ...args);
	}
	visitSubtractNode (subtractNode, ...args) {
		return this.visitInfixNode(subtractNode, ...args);
	}
	visitMultiplyNode (multiplyNode, ...args) {
		return this.visitInfixNode(multiplyNode, ...args);
	}
	visitDivideNode (divideNode, ...args) {
		return this.visitInfixNode(divideNode, ...args);
	}
	visitGreaterNode (greaterNode, ...args) {
		return this.visitInfixNode(greaterNode, ...args);
	}
	visitGreaterEqualNode (greaterEqualNode, ...args) {
		return this.visitInfixNode(greaterEqualNode, ...args);
	}
	visitLessNode (lessNode, ...args) {
		return this.visitInfixNode(lessNode, ...args);
	}
	visitLessEqualNode (lessEqualNode, ...args) {
		return this.visitInfixNode(lessEqualNode, ...args);
	}
	visitEqualNode (equalNode, ...args) {
		return this.visitInfixNode(equalNode, ...args);
	}
	visitNotEqualNode (notEqualNode, ...args) {
		return this.visitInfixNode(notEqualNode, ...args);
	}
	visitAndNode (andNode, ...args) {
		return this.visitInfixNode(andNode, ...args);
	}
	visitOrNode (orNode, ...args) {
		return this.visitInfixNode(orNode, ...args);
	}
	visitLiteralNode() {}
	visitIdentifierNode () {}
}
