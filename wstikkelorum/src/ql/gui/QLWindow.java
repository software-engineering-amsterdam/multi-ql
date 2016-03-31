package ql.gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import ql.FormParser;
import ql.SemanticAnalyser;
import ql.ast.form.Form;
import ql.ast.value.Value;
import ql.ast.visitor.Context;
import ql.ast.visitor.GuiVisitor;
import ql.gui.questionWidget.InputQuestionWidget;
import ql.gui.questionWidget.QuestionWidget;
import ql.issue.Issue;

public class QLWindow extends JFrame{
	private static final long serialVersionUID = 1L;
	private VisibleElements visibleUIElements;
	
	private Context context;
	private GuiVisitor<Object> guiVisitor;
	private Form form;
	private JFrame mainWindow;
	private JPanel mainPanel;
	private String currentPath;
	
	private List<Issue> warnings;
	
	public QLWindow(){
		createMainWindow();
		visibleUIElements = new VisibleElements();
		warnings = new ArrayList<Issue>();
	}
	
	private void ParseAndAnalyseForm(){
		try {
			form = FormParser.parseForm(currentPath);
			SemanticAnalyser semanticAnalyser = analyseForm(form);
			context = semanticAnalyser.getContext();
			guiVisitor = new GuiVisitor<>(context, this);
			
			if (semanticAnalyser.noIssues()) {
				warnings = semanticAnalyser.getWarnings();
				drawContent();
			} else {
				printIssues(semanticAnalyser.getContext());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static SemanticAnalyser analyseForm(Form form) {
		SemanticAnalyser semanticAnalyser = new SemanticAnalyser();
		semanticAnalyser.analyseForm(form);
		return semanticAnalyser;
	}
	
	private void printIssues(Context context) {
		visibleUIElements.removeAllUIElements();
		mainPanel.removeAll();
		mainPanel.setLayout(new GridLayout(context.numberOfIssues(), 1));
		Iterator<Issue> iterator = context.getIssueIterator();
		while(iterator.hasNext()){
			Issue issue = iterator.next();
			mainPanel.add(issue.getDrawableItem());
		}
		mainPanel.revalidate();
		mainPanel.repaint();
	}
	
	private void createMainWindow(){
		mainWindow = new JFrame();
		mainWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);
		mainWindow.setVisible(true);
		mainWindow.setSize(500, 500);
		mainWindow.setLayout(new FlowLayout());
		JMenuBar menubar = createMenuBar();
		mainWindow.setJMenuBar(menubar);
		mainPanel = new JPanel();
		mainWindow.add(mainPanel);
		mainPanel.revalidate();
	}
	
	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");
		JMenuItem menuItem = new JMenuItem("Open");
		menuItem.addActionListener(new FileChooserActionListener(menuBar));
		menu.add(menuItem);
		JMenuItem saveItem = new JMenuItem("Save");
		saveItem.addActionListener(new SaveActionListener());
		menu.add(saveItem);
		JMenuItem closeItem = new JMenuItem("Exit");
		closeItem.addActionListener(new CloseWindowActionListener());
		menu.add(closeItem);
		menuBar.add(menu);
		return menuBar;
	}
	
	private class SaveActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			FormSaver save = new FormSaver(currentPath, context);
			save.saveForm();			
		}
	}
	
	private class CloseWindowActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			mainWindow.dispose();
		}
	}
	
	private class FileChooserActionListener implements ActionListener{
		private JMenuBar parent;
		
		public FileChooserActionListener(JMenuBar parent) {
			this.parent = parent;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser jfc = new JFileChooser(new File("resources").getAbsolutePath());
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Question Language Forms", "ql");
			jfc.setFileFilter(filter);			
			int returnVal = jfc.showOpenDialog(parent);
			if(returnVal == JFileChooser.APPROVE_OPTION){
				currentPath = jfc.getSelectedFile().getAbsolutePath();
				ParseAndAnalyseForm();
			}
		}
	}
	
	public void drawContent(){
		mainWindow.setTitle(form.getIdentifier());
		updateContext();
		renewVisibleQuestion();
		getWarnings();
		drawVisibleQuestions();
		setValues();
	}

	private void getWarnings() {
		for(Issue issue : warnings){
			visibleUIElements.addIssue(issue);
		}
	}
	
	private void renewVisibleQuestion(){
		visibleUIElements.removeAllUIElements();
		visibleUIElements = guiVisitor.getVisibleQuestions(form, context);
	}
	
	private void updateContext(){
		Iterator<InputQuestionWidget> inputQuestions = visibleUIElements.getInputQuestionsIterator();
		while(inputQuestions.hasNext()){
			InputQuestionWidget inputQuestion = inputQuestions.next();
			Value value = inputQuestion.getInput();
			context.putValueForQuestion(inputQuestion.getQuestion(), value);
		}
	}
	
	private void drawVisibleQuestions(){
		mainPanel.removeAll();
		mainPanel.setLayout(new GridLayout(visibleUIElements.numberOfUIElements(), 1));
		Iterator<DrawableElement> iterator = visibleUIElements.getUIElementIterator();
		while(iterator.hasNext()){
			mainPanel.add(iterator.next().getDrawableItem());
		}
		mainPanel.revalidate();
		mainPanel.repaint();
	}
	
	private void setValues(){
		Iterator<QuestionWidget> visibleQuestionsIterator = visibleUIElements.getQuestionsIterator();
		while(visibleQuestionsIterator.hasNext()){
			QuestionWidget visibleQuestion = visibleQuestionsIterator.next();
			visibleQuestion.updateValueLabel(context.getValueForVariable(visibleQuestion.getVariable()));
		}
	}
}
