package org.uva.ql.ui.swing;

import javax.swing.JComponent;

import org.uva.ql.ui.UIComponent;

interface UISwingComponent extends UIComponent {

	public JComponent getComponent();
}
