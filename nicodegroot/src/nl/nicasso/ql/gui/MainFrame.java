package nl.nicasso.ql.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import net.miginfocom.swing.MigLayout;
import nl.nicasso.ql.gui.panels.Panel;

public class MainFrame implements Observer {

	private List<Panel> panels;
	private JFrame mainFrame;
	private JPanel rootPanel;
	private JScrollPane scrollFrame;
	
	public MainFrame() {
		panels = new ArrayList<Panel>();
		
		mainFrame = new JFrame("Questionnaire");
		mainFrame.setSize(800, 600);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setLayout(new MigLayout());
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		rootPanel = new JPanel();
		rootPanel.setLayout(new MigLayout());
		
		JScrollPane scrollFrame = new JScrollPane(rootPanel);
		mainFrame.setContentPane(scrollFrame);
	}
	
	public void setVisible(boolean visible) {
		mainFrame.setVisible(visible);
	}
	
	public void addPanel(Panel panel) {
		panels.add(panel);
	}
	
	public void addPanelsToMainFrame() {
		for (Panel p : panels) {
			rootPanel.add(p.getPanel(), "wrap");
		}
		
		setVisible(true);
	}

	@Override
	public boolean fieldValueChanged() {
		
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("UDPATE PANELS MAINFRAME");
		
		boolean runAgain = false;
		
		for (Panel p : panels) {
			boolean updatedPanel = p.fieldValueChanged();
			if (updatedPanel) {
				runAgain = true;
			}
		}
		
		if (runAgain) {
			System.out.println("RUN AGAIN!");
			fieldValueChanged();
		}
		
		return true;
	}

}