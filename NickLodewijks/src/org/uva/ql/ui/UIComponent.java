package org.uva.ql.ui;

import javax.swing.JComponent;

import org.uva.ql.QLInterpreterContext;

public interface UIComponent {

	public void setContext(QLInterpreterContext context);

	public JComponent getComponent();
}