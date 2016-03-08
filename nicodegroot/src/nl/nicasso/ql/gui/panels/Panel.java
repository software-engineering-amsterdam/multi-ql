package nl.nicasso.ql.gui.panels;

import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public abstract class Panel extends Component {

	/**
	 * Because Eclipse said so...
	 */
	private static final long serialVersionUID = 2587664050727551993L;
	
	private JPanel panel;

	public Panel() {
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	}

	public void addPanel(Panel subPanel) {
		panel.add(subPanel);
	}

	public void setVisible(boolean visible) {
		panel.setVisible(visible);
	}
	
	public JPanel getPanel() {
		return this.panel;
	}
	
}
