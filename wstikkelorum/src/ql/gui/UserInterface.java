package ql.gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import ql.ast.form.Form;
import ql.ast.visitor.Context;
import ql.ast.visitor.GuiVisitor;

public class UserInterface extends JFrame{
	private static final long serialVersionUID = 1L;
	private List<UIElement> visibleQuestions;
	private Context context;
	private GuiVisitor<Object> guiVisitor;
	private Form form;
	private JFrame mainWindow;
	private JPanel mainPanel;
	
	public UserInterface(Form form, Context context){
		this.form = form;
		this.context = context;
		this.guiVisitor = new GuiVisitor<>(context, this);
		visibleQuestions = new ArrayList<UIElement>();
		
		createMainWindow();
		drawContent();
	}
	
	private void createMainWindow(){
		mainWindow = new JFrame();
		mainWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);
		mainWindow.setVisible(true);
		mainWindow.setSize(600, 800);
		mainWindow.setTitle(form.getIdentifier());
		mainWindow.setLayout(new FlowLayout());
		JMenuBar menubar = createMenuBar();
		mainWindow.setJMenuBar(menubar);
		mainPanel = new JPanel();
		mainWindow.add(mainPanel);
	}
	
	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");
		JMenuItem menuItem = new JMenuItem("Open Questionaire");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser jfc = new JFileChooser();
				jfc.showOpenDialog(mainWindow);
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
		updateContext();
		renewVisibleQuestion();
		drawVisibleQuestions();
		setValues();
		
		System.out.println("Identifier - value");
		context.getIdentifierToValueMap().forEach((identifier, value) -> System.out.println(identifier + ' ' + value));
		System.out.println();
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
