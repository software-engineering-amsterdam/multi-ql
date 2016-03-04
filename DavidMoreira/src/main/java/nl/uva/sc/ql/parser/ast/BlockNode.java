package nl.uva.sc.ql.parser.ast;

import nl.uva.sc.ql.parser.Visitor;

public class BlockNode extends Node {

	private ListStatementsNode statements;
	
	public BlockNode(ListStatementsNode statements){
		this.statements = statements;
	}
	
	public ListStatementsNode getListStatements(){
		return this.statements;
	}
	
	@Override
	public String getType() {
		return "None";
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void dump() {
		System.out.println(this.getClass());
		getListStatements().dump();
	}
}
