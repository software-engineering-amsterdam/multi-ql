package org.uva.ql.ui;

import org.uva.ql.ast.form.ComputedQuestion;
import org.uva.ql.ast.form.Form;
import org.uva.ql.ast.form.InputQuestion;

public interface WidgetFactory {

	public QLQuestion create(ComputedQuestion question);

	public QLQuestion create(InputQuestion question);

	public QLForm create(Form form);
}
