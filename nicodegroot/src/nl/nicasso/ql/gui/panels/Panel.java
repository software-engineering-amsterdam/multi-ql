package nl.nicasso.ql.gui.panels;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public abstract class Panel {

	private JPanel panel;

	public Panel() {
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	}

	public void addPanel(Panel subPanel) {
		//panel.add(subPanel);
	}

	public void setVisible(boolean visible) {
		panel.setVisible(visible);
	}
	
	public JPanel getPanel() {
		return this.panel;
	}
	
}
