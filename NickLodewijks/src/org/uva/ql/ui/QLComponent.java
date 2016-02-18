package org.uva.ql.ui;

import javax.swing.JComponent;

import org.uva.ql.ast.expr.Context;

public interface QLComponent {

	public void setContext(Context context);

	public JComponent getComponent();
}