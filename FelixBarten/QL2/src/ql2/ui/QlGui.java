package ql2.ui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
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
	private JPanel questionPanel;
	private Form form;
	
	public QlGui(Form form, Context context) {
		if (form != null) {
			this.frameTitle = form.getFormID();
		}
		this.form = form;
		this.context = context;
		createWindow();
		questionPanel = new JPanel();
		GridLayout mgr = new GridLayout(0, 1);
		questionPanel.setLayout(mgr);
		questionPanel.add(new JLabel("Questions:"));
		populateWindow(form);
		JButton submitBtn = new JButton("Submit");
		submitBtn.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent evt) {
		    		storeValues();
		    }
		});		
		submitBtn.setMaximumSize(new Dimension(50, 25));
		this.add(questionPanel);
		this.add(submitBtn);
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
		this.setSize(500,800);
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
		menubar.add(edit);
		menubar.add(debug);
		menubar.add(help);
		return menubar;
	}

	public void storeValues() {
		// loop through questions to store values. 
		Component[] comps = questionPanel.getComponents();
		for (Component c : comps) {
			if (c instanceof UIInputQuestion) {
				context.addVariable(((UIInputQuestion) c).getQuestion().getQuestionID(), ((UIInputQuestion) c).getValue());
				System.out.println("Storing value: " + ((UIInputQuestion) c).getValue());
			}
		}
		// This method of storing seems convoluted. 
		redraw();
	}
	
	private void redraw() {
		questionPanel.removeAll();
		populateWindow(form);
		System.out.println("redraw complete");
		questionPanel.validate();
		questionPanel.repaint();
	}

	public JPanel getPanel() {
		return questionPanel;
	}
	
	public Context getContext() {
		return context;
	}
	
}
