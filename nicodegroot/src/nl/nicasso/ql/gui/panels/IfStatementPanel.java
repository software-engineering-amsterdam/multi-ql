package nl.nicasso.ql.gui.panels;

import javax.swing.JPanel;

public class IfStatementPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1855639862838735562L;
	
	private BlockPanel ifPanel;
	
	public IfStatementPanel() {
		ifPanel = new BlockPanel();
	}
	
	public void addIfPanel(Panel subPanel) {
		ifPanel.addPanel(subPanel);
	}
	
	@Override
	public JPanel getPanel() {
		System.out.println("GETPANEL IFSTATEMENT");
		return ifPanel.getPanel();
	}
	
	
}
