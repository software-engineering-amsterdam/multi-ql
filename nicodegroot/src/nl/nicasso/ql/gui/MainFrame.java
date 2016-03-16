package nl.nicasso.ql.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import net.miginfocom.swing.MigLayout;
import nl.nicasso.ql.ast.nodes.expressions.Identifier;
import nl.nicasso.ql.gui.evaluator.stateTable.StateTable;
import nl.nicasso.ql.gui.evaluator.stateTable.StateTableEntry;
import nl.nicasso.ql.gui.evaluator.values.Value;
import nl.nicasso.ql.gui.panels.Panel;
import nl.nicasso.ql.semanticAnalysis.messageHandling.Message;

public class MainFrame implements Observer {

	// Do these need to be global?
	private List<Panel> panels;
	private JFrame mainFrame;
	private JPanel rootPanel;
	private StateTable stateTable;
	
	public MainFrame(StateTable stateTable, List<Message> messages) {
		this.stateTable = stateTable;
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
		
		updateAllPanels();
		setVisible(true);
	}

	@Override
	public boolean fieldValueChanged(Identifier identifier, Value value) {
		
		StateTableEntry entry = new StateTableEntry(value);
		stateTable.addState(identifier, entry);
		
		return true;
	}
	
	public void updateAllPanels() {
		boolean runAgain = false;
		
		for (Panel p : panels) {
			boolean updatedPanel = p.update();
			if (updatedPanel) {
				runAgain = true;
			}
		}
		
		if (runAgain) {
			updateAllPanels();
		}
	
	}

}