package org.uva.sea.ql.gui.widgets;

import java.awt.Component;

import javax.swing.JComponent;
import javax.swing.JLabel;

import org.antlr.grammar.v3.ANTLRParser.finallyClause_return;

public abstract class Widget<T> extends Component {
	
	private final JComponent widget;
	private final JLabel label;
	
	public Widget(JComponent value, String label) {
		this.widget = value;
		this.label = new JLabel(label);
	}
	
	public JComponent getWidget() {
		return this.widget;
	}
	
	public JLabel getLabel() {
		return this.label;
	}

}