package ql.ast;

import java.util.List;

import ql.ASTNode;
import ql.BaseVisitor;

public class Questionnaire extends ASTNode {
	private List<Form> forms;

	public Questionnaire(List<Form> result) {
		// TODO Auto-generated constructor stub
		setForms(result);
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
		return null;
	}
	
}
