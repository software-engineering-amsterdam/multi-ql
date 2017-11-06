package ql2.ast;

import ql2.BaseVisitor;

public class Form extends ASTNode {

	private String formID;
	private Block formContent;
	
	public Form(String id, Block block) {
		this.formID = id;
		this.formContent = block;
	}
	
	public String getFormID() {
		return formID;
	}

	public void setFormID(String formID) {
		this.formID = formID;
	}

	public Block getFormContent() {
		return formContent;
	}

	public void setFormContent(Block formContent) {
		this.formContent = formContent;
	}

	@Override
	public <T> T accept(BaseVisitor<T> visitor) {
		return visitor.visit(this);
	}

}
