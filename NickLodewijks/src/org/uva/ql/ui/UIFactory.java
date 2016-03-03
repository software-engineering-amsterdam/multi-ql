package org.uva.ql.ui;

import org.uva.ql.ast.form.QLForm;
import org.uva.ql.ast.stat.QLQuestion;

public interface UIFactory {

	public UIQuestionnaire create(QLForm form);

	public UIQuestion create(QLQuestion question);
}
