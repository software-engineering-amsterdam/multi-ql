package org.uva.ql.domain;

import org.uva.ql.ast.form.QLQuestionnaire;

public class QuestionnaireFactory {

	public static Questionnaire create(QLQuestionnaire q) {
		Questionnaire questionnaire;

		questionnaire = new Questionnaire();

		q.getForms().forEach(qlForm -> {
			Form form;

			form = FormFactory.create(qlForm);
			questionnaire.addForm(form);
		});

		return questionnaire;
	}

	private QuestionnaireFactory() {
	}
}
