package nl.uva.sc.ql.parser.ast;

import nl.uva.sc.ql.parser.Visitor;

public class FormNode extends Node {

	private String name;
	
	public FormNode(String name){
		this.name = name;
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
		if (getLeft() != null) { getLeft().dump(); }
		if (getRight() != null) { getRight().dump(); }
	}

	public String getName() {
		return name;
	}

}
