package org.uva.ql.ui.swing;

import javax.swing.JComponent;

import org.uva.ql.ui.UIQuestion;

public interface UISwingQuestion extends UIQuestion {

	@Override
	public UISwingWidget getLabelWidget();

	@Override
	public UISwingWidget getValueWidget();

	public JComponent getComponent();

}
