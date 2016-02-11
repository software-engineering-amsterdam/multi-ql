package org.uva.ql.ui;

import javax.swing.JComponent;

import org.uva.ql.ast.expr.Context;

public interface QLWidget {
	public void setContext(Context context);

	public JComponent getComponent();
}