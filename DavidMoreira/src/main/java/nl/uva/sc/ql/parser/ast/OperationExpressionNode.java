package nl.uva.sc.ql.parser.ast;


public abstract class OperationExpressionNode extends Node {

	private String symbol;
	
	public void init(Node left, Node right, String symbol){
		super.init(left, right);
		this.symbol = symbol;
	}
	
	public String getSymbol(){
		return this.symbol;
	}
			
	@Override
	public void dump() {
		System.out.println(this.getClass());
		if (getLeft() != null) { getLeft().dump(); }
		if (getRight() != null) { getRight().dump(); }
	}

}
