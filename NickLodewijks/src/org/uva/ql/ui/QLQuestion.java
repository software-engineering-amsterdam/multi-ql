package org.uva.ql.ui;

import javax.swing.JComponent;

import org.uva.ql.ast.expr.Context;

public class QLQuestion {

	private final QLWidget label;
	private final QLWidget input;

	public QLQuestion(QLWidget label, QLWidget input) {
		this.label = label;
		this.input = input;
	}

	public void setContext(Context context) {
		label.setContext(context);
		input.setContext(context);
	}

	public JComponent getLabelComponent() {
		return label.getComponent();
	}

	public JComponent getInputComponent() {
		return input.getComponent();
	}

}
