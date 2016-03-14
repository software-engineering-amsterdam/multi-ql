package org.uva.sea.ql.gui.panels;


import javax.swing.BoxLayout;
import javax.swing.JPanel;

//extend Component or .getPanel?

public abstract class Panel extends JPanel {

	private JPanel panel;
	
	public Panel() {
		this.panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

	}

	public JPanel getPanel() {
		return panel;
	}

	public void addPanel(Panel argPanel) {
		panel.add(argPanel.getPanel());
	}
	
}