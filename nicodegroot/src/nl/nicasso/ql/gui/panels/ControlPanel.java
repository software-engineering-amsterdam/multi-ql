package nl.nicasso.ql.gui.panels;

import javax.swing.JPanel;

import nl.nicasso.ql.gui.widgets.Button;

public class ControlPanel extends Panel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8949517263692772650L;
	
	private JPanel panel;

	public ControlPanel() {
		panel = new JPanel();
		
		addButton("Reset");
	}
	
	public void addButton(String label) {
		Button submit = new Button(label); 
		panel.add(submit.getWidget());
	}
	
	@Override
	public JPanel getPanel() {
		System.out.println("GETPANEL CONTROLPANEL");
		return this.panel;
	}
	
}