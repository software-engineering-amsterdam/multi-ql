import {NodeVisitor} from 'src/ast';

class WalkingVisitor {
	constructor(listener) {
		this.listener = listener;
	}
	visitFormNode (formNode) {
		this.listener.enterFormNode(formNode);
		formNode.block.accept(this);
		this.listener.leaveFormNode(formNode);
	}
	visitBlockNode (blockNode) {
		this.listeners.enterBlockNode(blockNode);
		for (let statement of blockNode.statements) {
			statement.accept(this);
		}
		this.listeners.leaveBlockNode(blockNode);
	}
	visitIfNode (ifNode) {
		this.listener.enterIfNode(ifNode);
		ifNode.condition.accept(this);
		ifNode.thenBlock.accept(this);
		if (ifNode.elseBlock !== null) {
			ifNode.elseBlock.accept(this);
		}
		this.listener.leaveIfNode(ifNode);
	}
	visitQuestionNode (questionNode) {
		this.listener.enterQuestionNode(questionNode);
		this.listener.leaveQuestionNode(questionNode);
	}
	visitUnaryPrefixNode (unaryPrefixNode) {
		this.listener.enterUnaryPrefixNode(unaryPrefixNode);
		unaryPrefixNode.operand.accept(this);
		this.listener.leaveUnaryPrefixNode(unaryPrefixNode);
	}
	visitInfixNode (infixNode) {
		this.listener.enterInfixNode(infixNode);
		infixNode.leftOperand.accept(this);
		infixNode.rightOperand.accept(this);
		this.listener.leaveInfixNode(infixNode);
	}
	visitLiteralNode (literalNode) {
		this.listener.enterLiteralNode(literalNode);
		this.listener.leaveLiteralNode(literalNode);
	}
}

export class NodeWalker {
	walk(listener, node) {
		return node.accept(new WalkingVisitor(listener));
	}
}

export class NodeListener {
	enterFormNode (formNode) {}
	leaveFormNode (formNode) {}
	enterBlockNode (blockNode) {}
	leaveBlockNode (blockNode) {}
	enterIfNode (ifNode) {}
	leaveIfNode (ifNode) {}
	enterQuestionNode (questionNode) {}
	leaveQuestionNode (questionNode) {}
	enterUnaryPrefixNode (unaryPrefixNode) {}
	leaveUnaryPrefixNode (unaryPrefixNode) {}
	enterInfixNode (infixNode) {}
	leaveInfixNode (infixNode) {}
	enterLiteralNode (literalNode) {}
	leaveLiteralNode (literalNode) {}
}
