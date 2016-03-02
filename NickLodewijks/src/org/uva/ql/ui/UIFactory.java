package org.uva.ql.ui;

import org.uva.ql.domain.Form;
import org.uva.ql.domain.Question;
import org.uva.ql.domain.Questionnaire;

public interface UIFactory<T> {

	public UIQuestionnaire create(Questionnaire questionnaire);

	public UIComponent<T> create(Form form);

	public UIComponent<T> create(Question question);

	public UIWidget<T> createLabelWidget(Question question);

	public UIWidget<T> createValueWidget(Question question);
}
