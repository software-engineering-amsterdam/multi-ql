package nl.nicasso.ql.gui.panels;

import javax.swing.JPanel;

public class IfElseStatementPanel extends IfStatementPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6566895871572883429L;
	
	private BlockPanel elsePanel;
	
	public IfElseStatementPanel() {
		super();
		elsePanel = new BlockPanel();
	}
	
	public void addElsePanel(Panel subPanel) {
		elsePanel.addPanel(subPanel);
	}
	
	@Override
	public JPanel getPanel() {
		System.out.println("GETPANEL IFELSESTATEMENT");
		// @TODO Let this depend on the evaluation
		return elsePanel.getPanel();
	}
	
}
