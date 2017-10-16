package ql2.ast;

import ql2.ASTNode;
import ql2.BaseVisitor;

public class Form extends ASTNode {

	private String formID;
	private Block formContent;
	
	
	public Form(String result, Block result2) {
		formID = result;
		formContent = result2;
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
