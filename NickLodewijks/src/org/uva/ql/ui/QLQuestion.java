package org.uva.ql.ui;

import javax.swing.JComponent;

import org.uva.ql.ast.expr.Context;

public interface QLQuestion {

	public void setContext(Context context);

	public JComponent getLabelComponent();

	public JComponent getInputComponent();
}
