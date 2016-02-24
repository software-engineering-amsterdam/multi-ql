package org.uva.ql.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QLQuestionaire {

	private List<QLForm> forms = new ArrayList<QLForm>();

	public QLQuestionaire() {

	}

	public void addForm(QLForm form) {
		forms.add(form);
	}

	public List<QLForm> getForms() {
		return Collections.unmodifiableList(forms);
	}
}
