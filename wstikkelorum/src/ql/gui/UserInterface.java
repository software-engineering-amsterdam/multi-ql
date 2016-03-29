package ql.gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

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
import ql.ast.visitor.Context;
import ql.ast.visitor.GuiVisitor;
import ql.issue.Issue;

public class UserInterface extends JFrame{
	private static final long serialVersionUID = 1L;
	private VisibleQuestions visibleQuestions;
	
	private Context context;
	private GuiVisitor<Object> guiVisitor;
	private Form form;
	private JFrame mainWindow;
	private JPanel mainPanel;
	private String currentPath;
	
	public UserInterface(){
		createMainWindow();
		visibleQuestions = new VisibleQuestions();
	}
	
	private void ParseAndAnalyseForm(){
		try {
			form = FormParser.parseForm(currentPath, false);
			SemanticAnalyser semanticAnalyser = analyseForm(form);
			context = semanticAnalyser.getContext();
			this.guiVisitor = new GuiVisitor<>(context, this);
			
			if (semanticAnalyser.noIssues()) {
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
		semanticAnalyser.printData();
		return semanticAnalyser;
	}
	
	private void printIssues(Context context) {
		mainPanel.removeAll();
		mainPanel.setLayout(new GridLayout(context.getIssues().size(), 1));
		for (Issue issue : context.getIssues()) {
			mainPanel.add(issue.getDrawableItem());
		}
		mainPanel.revalidate();
		mainPanel.repaint();
	}
	
	private void createMainWindow(){
		mainWindow = new JFrame();
		mainWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);
		mainWindow.setVisible(true);
		mainWindow.setSize(400, 600);
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
		JMenuItem menuItem = new JMenuItem("Open Questionaire");
		menuItem.addActionListener(new FileChooserActionListener(menuBar));
		menu.add(menuItem);
		JMenuItem closeItem = new JMenuItem("Exit");
		closeItem.addActionListener(new CloseWindowActionListener());
		menu.add(closeItem);
		menuBar.add(menu);
		return menuBar;
	}
	
	class CloseWindowActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			mainWindow.dispose();
		}
	}
	
	class FileChooserActionListener implements ActionListener{
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
		drawVisibleQuestions();
		setValues();
	}
	
	private void renewVisibleQuestion(){
		visibleQuestions.removeAllQuestions();
		visibleQuestions = guiVisitor.getVisibleQuestions(form, context);
	}
	
	private void updateContext(){
		Iterator<InputQuestionWidget> inputQuestions = visibleQuestions.getInputQuestionsIterator();
		while(inputQuestions.hasNext()){
			InputQuestionWidget inputQuestion = inputQuestions.next();
			Object value = inputQuestion.getInput();
			context.putValueForQuestion(inputQuestion.getQuestion(), value);
		}
	}
	
	private void drawVisibleQuestions(){
		mainPanel.removeAll();
		mainPanel.setLayout(new GridLayout(visibleQuestions.numberOfQuestions(), 1));
		Iterator<UIElement> iterator = visibleQuestions.getIterator();
		while(iterator.hasNext()){
			mainPanel.add(iterator.next().getDrawableItem());
		}
		mainPanel.revalidate();
		mainPanel.repaint();
	}
	
	//TODO: duplicated code..
	private void setValues(){
		Iterator<InputQuestionWidget> inputQuestionIterator = visibleQuestions.getInputQuestionsIterator();
		while(inputQuestionIterator.hasNext()){
			InputQuestionWidget inputQuestionWidget = inputQuestionIterator.next();
			inputQuestionWidget.updateValueLabel(context.getValueForVariable(inputQuestionWidget.getQuestion().getVariable()));
		}
		
		Iterator<ComputedQuestionWidget> computedQuestionIterator = visibleQuestions.getComputedQuestionsIterator();
		while(computedQuestionIterator.hasNext()){
			ComputedQuestionWidget computedQuestionWidget = computedQuestionIterator.next();
			computedQuestionWidget.updateValueLabel(context.getValueForVariable(computedQuestionWidget.getQuestion().getVariable()));
		}
	}
}
