package org.uva.ql.ui;

import javax.swing.JComponent;

import org.uva.ql.ast.expr.Context;

public interface QLForm {

	public void setContext(Context context);

	public void addSection(QLSection section);

	public void addQuestion(QLQuestion question);

	public JComponent getComponent();
}
