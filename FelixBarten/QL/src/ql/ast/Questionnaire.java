package ql.ast;

import java.util.List;

public class Questionnaire {
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
	
}
