package nl.nicasso.ql.gui.panels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class BlockPanel extends Panel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4055866889146394322L;
	
	private List<Panel> panels;

	private JPanel panel;
	
	public BlockPanel() {
		panels = new ArrayList<Panel>();
		panel = new JPanel();
	}
	
	@Override
	public void addPanel(Panel subPanel) {
		panels.add(subPanel);
	}
	
	public List<Panel> getPanels() {
		return this.panels;
	}
	
	public JPanel getPanel() {

		for (Panel p : panels) {
			panel.add(p.getPanel());
		}
		
		panel.setVisible(true);
		
		return panel;
	}
	

}