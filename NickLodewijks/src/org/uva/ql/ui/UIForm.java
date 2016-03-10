package org.uva.ql.ui;

import javax.swing.JComponent;

public interface UIForm {

	public void addQuestion(UIQuestion question);

	public JComponent getComponent();
}
