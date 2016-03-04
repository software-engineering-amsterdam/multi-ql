package nl.uva.sc.ql.parser.ast;

import nl.uva.sc.ql.parser.Visitor;

public class ConditionBlockNode extends Node {
	
	private ExpressionNode expression;
	private BlockNode block;
	
	public ConditionBlockNode(ExpressionNode expression, BlockNode statement){
		this.expression = expression;
		this.block = statement;
	}
	
	@Override
	public String getType() {
		return "None";
	}
	
	public ExpressionNode getExpression(){
		return this.expression;
	}
	
	public BlockNode getBlock(){
		return this.block;
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void dump() {
		System.out.println(this.getClass());
		getExpression().dump();
		getBlock().dump();
	}
}
