package nl.nicasso.ql.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

public class MainWindow implements NotifyAboutGuiUpdates {

	private List<Panel> panels;
	private JFrame mainFrame;
	private JPanel rootPanel;
	private StateTable stateTable;

	public MainWindow(StateTable stateTable, List<Message> messages) {
		this.stateTable = stateTable;

		panels = new ArrayList<Panel>();

		setupScrollableMainFrame();

		displayPossibleMessages(messages);
	}

	private void setupScrollableMainFrame() {
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

	private void displayPossibleMessages(List<Message> messages) {
		if (messages.size() > 0) {
			displayMessages(messages);
		}
	}

	private void displayMessages(List<Message> messages) {
		JPanel messagePanel = new JPanel();
		messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.PAGE_AXIS));

		JLabel header = new JLabel("Messages:");
		messagePanel.add(header);

		for (Message message : messages) {
			JLabel label = new JLabel(message.getMessageType() + ": " + message.getMessage());
			messagePanel.add(label);
		}

		rootPanel.add(messagePanel, "wrap");
		setVisible(true);
	}

	public void setVisible(boolean visible) {
		mainFrame.setVisible(visible);
	}

	public void add(Panel panel) {
		panels.add(panel);
	}

	public void addPanelsToMainFrame() {
		for (Panel p : panels) {
			rootPanel.add(p.getPanel(), "wrap");
		}

		updateGUIPanels();
		setVisible(true);
	}

	@Override
	public void updateValueInStateTable(Identifier identifier, Value value) {
		StateTableEntry entry = new StateTableEntry(value);
		stateTable.add(identifier, entry);
	}

	public void updateGUIPanels() {
		boolean runAgain = false;

		for (Panel p : panels) {
			boolean updatedPanel = p.update();
			if (updatedPanel) {
				runAgain = true;
			}
		}

		if (runAgain) {
			updateGUIPanels();
		}

	}

}