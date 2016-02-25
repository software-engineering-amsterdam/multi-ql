package org.uva.ql.ui;

import org.uva.ql.domain.Questionnaire;

public interface UIFactory {

	public UIQuestionnaire create(Questionnaire questionnaire);
}
