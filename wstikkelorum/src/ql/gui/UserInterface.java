package ql.gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
import ql.ast.visitor.Context;
import ql.ast.visitor.GuiVisitor;
import ql.issue.Issue;

public class UserInterface extends JFrame{
	private static final long serialVersionUID = 1L;
	private List<UIElement> visibleQuestions;
	private Context context;
	private GuiVisitor<Object> guiVisitor;
	private Form form;
	private JFrame mainWindow;
	private JPanel mainPanel;
	private String currentPath;
	
	public UserInterface(){
		createMainWindow();
		visibleQuestions = new ArrayList<UIElement>();
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
			// TODO print error!!
			e.printStackTrace();
		}
	}
	
	private static SemanticAnalyser analyseForm(Form form) {
		SemanticAnalyser semanticAnalyser = new SemanticAnalyser();
		semanticAnalyser.analyseForm(form);
		semanticAnalyser.printData();
		return semanticAnalyser;
	}
	
	private static void printIssues(Context context) {
		for (Issue issue : context.getIssues()) {
			issue.print();
		}
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
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser(new File("resources").getAbsolutePath());
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Question Language Forms", "ql");
				jfc.setFileFilter(filter);			
				int returnVal = jfc.showOpenDialog(menuBar);
				if(returnVal == JFileChooser.APPROVE_OPTION){
					currentPath = jfc.getSelectedFile().getAbsolutePath();
					ParseAndAnalyseForm();
				}
			}
		});
		menu.add(menuItem);
		JMenuItem closeItem = new JMenuItem("Exit");
		closeItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainWindow.dispose();
			}
		});
		menu.add(closeItem);
		menuBar.add(menu);
		return menuBar;
	}
	
	public void drawContent(){
		mainWindow.setTitle(form.getIdentifier());
		updateContext();
		renewVisibleQuestion();
		drawVisibleQuestions();
		setValues();
	}
	
	private void renewVisibleQuestion(){
		visibleQuestions.clear();
		visibleQuestions = guiVisitor.getVisibleQuestions(form, context);
	}
	
	private void updateContext(){
		for(UIElement question : visibleQuestions){
			if(question instanceof InputQuestionWidget){
				Object value = ((UserInputElement) question).getInput();
				if(value == null){
					continue;
				}else{
					context.putValueForQuestion(((InputQuestionWidget) question).getQuestion(), value);
				}
			}
		}
	}
	
	private void drawVisibleQuestions(){
		mainPanel.removeAll();
		mainPanel.setLayout(new GridLayout(visibleQuestions.size(), 1));
		for(UIElement question : visibleQuestions){
			mainPanel.add(question.getDrawableItem());
		}
		mainPanel.revalidate();
		mainPanel.repaint();
	}
	
	private void setValues(){
		for(UIElement question : visibleQuestions){
			if(question instanceof InputQuestionWidget){
				question.updateValueLabel(context.getValueForVariable(((InputQuestionWidget) question).getQuestion().getVariable()));
			}
			if(question instanceof ComputedQuestionWidget){
				question.updateValueLabel(context.getValueForVariable(((ComputedQuestionWidget) question).getQuestion().getVariable()));
			}
		}
	}
}
