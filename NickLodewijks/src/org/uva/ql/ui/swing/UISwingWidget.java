package org.uva.ql.ui.swing;

import javax.swing.JComponent;

import org.uva.ql.ui.UIWidget;

interface UISwingWidget extends UIWidget {

	public JComponent getComponent();

}
