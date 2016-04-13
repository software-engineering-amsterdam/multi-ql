package uva.ql.ast;

import uva.ql.visitors.INodeVisitor;


public class Form extends Block {

	private String name;
	
	public Form(String name, int startLine, int startColumn) {
		super(null, startLine, startColumn);
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public void accept(INodeVisitor visitor) {
		visitor.visitForm(this);
	}
}
