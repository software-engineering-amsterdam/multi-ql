package nl.nicasso.ql.gui;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import nl.nicasso.ql.gui.panels.Panel;

public class MainFrame {

	private List<Panel> panels;
	private JFrame mainFrame;
	
	public MainFrame() {
		panels = new ArrayList<Panel>();
		
		mainFrame = new JFrame("Questionnaire");
		mainFrame.setSize(1024, 768);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setLayout(new GridLayout(0, 1));
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	public void setVisible(boolean visible) {
		mainFrame.setVisible(visible);
	}
	
	public void addPanel(Panel panel) {
		panels.add(panel);
	}
	
	public void updateMainFrame() {
		for (Panel p : panels) {
			System.out.println("ADD PANEL TO MAINFRAME: "+p.getClass());
			mainFrame.add(p.getPanel());
		}
		
		setVisible(true);
	}

}