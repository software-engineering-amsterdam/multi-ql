package org.uva.ql.ui;

import javax.swing.JComponent;

import org.uva.ql.ast.form.ComputedQuestion;
import org.uva.ql.ast.form.InputQuestion;

public interface WidgetFactory {
	public JComponent create(ComputedQuestion question);

	public JComponent create(InputQuestion question);
}
