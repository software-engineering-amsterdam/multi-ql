package nl.uva.sc.ql.compiler.parser.ast;

public abstract class UnaryExpression extends ExpressionNode {
	private ExpressionNode expression;
	
	public UnaryExpression(ExpressionNode expression){
		this.expression = expression;
	}
	
	public ExpressionNode getExpression(){
		return this.expression;
	}
	
	@Override
	public void dump() {
		System.out.println(getClass());
		getExpression().dump();
	}
}
