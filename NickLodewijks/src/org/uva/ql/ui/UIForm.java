package org.uva.ql.ui;

import javax.swing.JComponent;

import org.uva.ql.QLContext;

public interface UIForm {

	public void setContext(QLContext context);

	public void addQuestion(UIQuestion question);

	public JComponent getComponent();
}
