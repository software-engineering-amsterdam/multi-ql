package nl.nicasso.ql.gui.panels;

import javax.swing.JPanel;

public class IfStatementPanel extends Panel {

	private BlockPanel ifPanel;
	
	public IfStatementPanel() {
		ifPanel = new BlockPanel();
	}
	
	public void addIfPanel(Panel subPanel) {
		ifPanel.addPanel(subPanel);
	}
	
	@Override
	public JPanel getPanel() {
		return ifPanel.getPanel();
	}
	
	
}
