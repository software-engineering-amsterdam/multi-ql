package org.uva.ql.ui;

import org.uva.ql.domain.QLQuestionaire;

public interface UIFactory {

	public UIQuestionnaire create(QLQuestionaire questionnaire);
}
