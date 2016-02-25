package org.uva.ql.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Questionnaire {

	private List<Form> forms = new ArrayList<Form>();

	public Questionnaire() {

	}

	public void addForm(Form form) {
		forms.add(form);
	}

	public List<Form> getForms() {
		return Collections.unmodifiableList(forms);
	}
}
