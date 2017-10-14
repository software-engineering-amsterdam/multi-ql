package ql2.ast;

import java.util.List;

import ql2.ASTNode;
import ql2.BaseVisitor;

public class Questionnaire extends ASTNode  {

	private List<Form> forms;
	
	public Questionnaire(List<Form> result) {
		forms = result;
	}

	public List<Form> getForms() {
		return forms;
	}

	public void setForms(List<Form> forms) {
		this.forms = forms;
	}

	@Override
	public <T> T accept(BaseVisitor<T> visitor) {
		// TODO Auto-generated method stub
		return visitor.visit(this);
	}

}
