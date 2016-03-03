package org.uva.ql.ui;

import org.uva.ql.domain.Form;
import org.uva.ql.domain.Question;
import org.uva.ql.domain.Questionnaire;

public interface UIFactory {

	public UIQuestionnaire create(Questionnaire questionnaire);

	public UIForm create(Form form);

	public UIQuestion create(Question question);
}
