package uva.ql.ast;

import uva.ql.visitors.INodeVisitor;

public class Form extends Block {

	private String name;
	
	Form(AST newAST) {
		super(newAST);
	}
	
	@Override
	protected int getNodeType0() {
		return ANode.FORM;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public void accept(INodeVisitor visitor) {
		visitor.visitForm(this);
	}
}
