package org.uva.ql.ui;

import javax.swing.JComponent;

import org.uva.ql.ast.expr.Context;

public interface QLSection {

	public void setContext(Context context);

	public void addSubSection(QLSection section);

	public void addQuestion(QLQuestion question);

	public JComponent getComponent();
}
