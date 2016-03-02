package uva.ql.ast;

import uva.ql.interfaces.INode;
import uva.ql.visitors.INodeVisitor;

public class IfStatement extends ANode implements INode {

	private AExpression expression = null;
	private ANode leftNode = null;
	private ANode rightNode = null;
	
	IfStatement(AST ast) {
		super(ast);
	}

	@Override
	protected int getNodeType0() {
		return ANode.IF_STATEMENT;
	}
	
	public ANode getLeftNode() {
		return leftNode;
	}

	public void setLeftNode(ANode leftNode) {
		this.leftNode = leftNode;
	}

	public ANode getRightNode() {
		return rightNode;
	}

	public void setRightNode(ANode rightNode) {
		this.rightNode = rightNode;
	}

	public AExpression getExpression() {
		return expression;
	}

	public void setExpression(AExpression expression) {
		this.expression = expression;
	}

	@Override
	public void accept(INodeVisitor visitor) {
		visitor.visitIfStmnt(this);
	}

}
