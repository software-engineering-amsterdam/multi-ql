package org.uva.ql.ui;

import org.uva.ql.domain.Form;
import org.uva.ql.domain.Question;
import org.uva.ql.domain.Questionnaire;

public interface UIFactory {

	public UIQuestionnaire create(Questionnaire questionnaire);

	public UIComponent create(Form form);

	public UIComponent create(Question question);

	public UIWidget createLabelWidget(Question question);

	public UIWidget createValueWidget(Question question);
}
