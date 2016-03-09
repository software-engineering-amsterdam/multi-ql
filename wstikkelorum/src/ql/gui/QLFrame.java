package ql.gui;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ql.ast.form.Form;
import ql.ast.statement.ComputedQuestion;
import ql.ast.statement.IfStatement;
import ql.ast.statement.InputQuestion;
import ql.ast.statement.Question;
import ql.ast.statement.Statement;

public class QLFrame extends JFrame{
	private JFrame mainFrame;
	private JPanel controlPanel;
	private List<UIElement> visibleQuestions;
	private static final long serialVersionUID = 1L;

	public QLFrame(Form form){
		visibleQuestions = new ArrayList<UIElement>();
		//Hier kan ik misschien een visitor pattern gebruiken om alle questions die ik tegenkom
		//te tekenen en als ik een ifstatement tegenkom te tekenen (adden) als de expression true is
		
		for(Statement s : form.getBody().getStatements()){
			if(s.getQuestion() != null){
				addQuestionWidget(s.getQuestion());
			}
			if(s.getIfStatement() != null){
				//TODO:evalute the condition
				addIfStatementBody(s.getIfStatement());
			}
		}
		
		drawVisibleQuestions();
	}
	
	private void addIfStatementBody(IfStatement ifStatement){
		for (Statement s : ifStatement.getBody().getStatements()){
			if(s.getQuestion() != null){
				addQuestionWidget(s.getQuestion());
			}
			if(s.getIfStatement() != null){
				//TODO:evalute the condition
				addIfStatementBody(s.getIfStatement());
			}
		}
	}
	
	private void addQuestionWidget(Question question){
		if(question instanceof InputQuestion){
			UIElement questionWidget = new InputQuestionWidget((InputQuestion)question);
			visibleQuestions.add(questionWidget);
		}else{
			UIElement questionWidget = new ComputedQuestionWidget((ComputedQuestion)question);
			visibleQuestions.add(questionWidget);
		}
	}
	
	private void drawVisibleQuestions(){
		mainFrame = new JFrame();
		mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		controlPanel = new JPanel();
		controlPanel.setLayout(new GridLayout(visibleQuestions.size(), 1));
		
		for(UIElement element : visibleQuestions){
			controlPanel.add(element.getPanel());
		}	
		
		mainFrame.add(controlPanel);
		mainFrame.setVisible(true);
		mainFrame.setSize(600, 800);
	}
}
