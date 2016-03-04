package nl.uva.sc.ql.parser.ast;

public abstract class BinaryExpressionNode extends ExpressionNode {
	private ExpressionNode left;
	private ExpressionNode right;
	
	public BinaryExpressionNode(ExpressionNode left, ExpressionNode right){
		this.left = left;
		this.right = right;
	}
	
	public ExpressionNode getLeft(){
		return this.left;
	}
	
	public ExpressionNode getRight(){
		return this.right;
	}
	
	@Override
	public void dump() {
		System.out.println(getClass());
		getLeft().dump();
		getRight().dump();
	}
}
