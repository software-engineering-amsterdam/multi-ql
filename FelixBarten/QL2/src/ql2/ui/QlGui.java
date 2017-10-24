package ql2.ui;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import ql2.Context;
import ql2.ast.Form;
import ql2.ast.Questionnaire;

public class QlGui extends JFrame{

	private static final long serialVersionUID = -7110429450534548202L;
	private String frameTitle = "Questionnaire";
	private Context context;
	
	private JPanel panel;
	
	public QlGui(Form form, Context context) {
		if (form != null) {
			this.frameTitle = form.getFormID();
		}
		this.context = context;
		createWindow();
		panel = new JPanel();
		panel.add(new JLabel("Questions:"));
		populateWindow(form);
		this.add(panel);
		this.setVisible(true);
	}
	
	public QlGui(Questionnaire quest) {
		createWindow();
		this.setVisible(true);
	}
	
	private void populateWindow(Form form) {
		UIVisitor visitor = new UIVisitor(context, this); 
		visitor.visit(form);
		context = visitor.getContext();
	}
	
	private void createWindow() {
		this.setSize(500,500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setTitle(frameTitle);
		
		this.setJMenuBar(createMenu());
		
	}

	private JMenuBar createMenu() {
		JMenuBar menubar = new JMenuBar();
		JMenu file, edit, debug, help;
		
		file = new JMenu("File");
		edit = new JMenu("Edit");
		debug = new JMenu("Debug");
		help = new JMenu("Help");
		
		JMenuItem exit = new JMenuItem("Exit");
		JMenuItem showSource = new JMenuItem("Show QL Source");
		JMenuItem askHelp = new JMenuItem("Help"); 
		
		file.add(exit);
		debug.add(showSource);
		help.add(askHelp);
		
		menubar.add(file);
		menubar.add(debug);
		menubar.add(help);
		return menubar;
	}

	public JPanel getPanel() {
		return panel;
	}
}
