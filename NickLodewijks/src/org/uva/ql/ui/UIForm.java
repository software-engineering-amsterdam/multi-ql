package org.uva.ql.ui;

import javax.swing.JComponent;

import org.uva.ql.UIContext;

public interface UIForm {

	public void setContext(UIContext context);

	public void addQuestion(UIQuestion question);

	public JComponent getComponent();
}
