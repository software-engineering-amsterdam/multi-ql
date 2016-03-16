package nl.uva.sc.ql.compiler.parser.ast;

import nl.uva.sc.ql.compiler.typechecker.Visitor;

public class FormNode extends Node {

	private String name;
	private BlockNode block;
	
	public FormNode(String name, BlockNode block){
		this.name = name;
		this.block = block;
	}

	public String getName() {
		return name;
	}
	
	public BlockNode getBlock(){
		return this.block;
	}
	
	@Override
	public String getType() {
		return this.name;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void dump() {
		System.out.println(this.getClass());
		getBlock().dump();
	}

}
