package nl.uva.sc.ql.parser.ast;

public abstract class ExpressionNode extends Node {

	private String symbol;
	
	public void init(Node left, Node right, String symbol){
		super.init(left, right);
		this.symbol = symbol;
	}
	
	public String getSymbol(){
		return this.symbol;
	}

}
