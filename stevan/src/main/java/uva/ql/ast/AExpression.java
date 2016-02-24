package uva.ql.ast;

import uva.ql.interfaces.IExpression;
import uva.ql.interfaces.INode;
import uva.ql.interfaces.INodeVisitor;

public abstract class AExpression extends ANode implements IExpression {
	
	private int exprType = 0;	
	private AExpression leftNode = null;
	private AExpression rightNode = null;
	
	protected AExpression(AST ast) {
		super(ast);
		
		setExprType(getExprType0());
	}
	
	@Override
	protected int getNodeType0() {
		return INode.EXPRESSION;
	}

	protected abstract int getExprType0();

	public final int getExprType() {
		return this.exprType;
	}
	
	private void setExprType(int exprType) {
		this.exprType = exprType;
	}

	public AExpression getLeftNode() {
		return leftNode;
	}

	public AExpression getRightNode() {
		return rightNode;
	}
	
	public void setLeftNode(AExpression leftNode) {
		this.leftNode = leftNode;
	}

	public void setRightNode(AExpression rightNode) {
		this.rightNode = rightNode;
	}
	
	@Override
	public void accept(INodeVisitor visitor) {
		visitor.visitExp(this);
	}
}
