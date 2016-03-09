package ql.gui;

import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import ql.ast.form.Form;
import ql.ast.statement.IfStatement;
import ql.ast.statement.Question;
import ql.ast.statement.Statement;

public class QLFrame extends JFrame{
	private JFrame mainFrame;
	private JPanel controlPanel;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public QLFrame(Form form){
		//create a new frame
		mainFrame = new JFrame(form.getIdentifier());
		
		//display all the upperlevel questions
		controlPanel = new JPanel();
		
		//voor if statements een methods maken die telt hoeveel questions ze hebben en dus ruimte
		//ze nodig hebben anders worden if statements steeds kleiner getekened
		int n = form.getBody().getStatements().size();
		GridLayout layout = new GridLayout(n, 1);
		controlPanel.setLayout(layout);
		
		for(Statement s : form.getBody().getStatements()){
			if (s.getQuestion() != null){
				JPanel panel = drawQuestion(s.getQuestion());
				controlPanel.add(panel);
			}
			if (s.getIfStatement() != null){
				JPanel panel = drawIfStatement(s.getIfStatement());
				controlPanel.add(panel);
			}
		}
		
		mainFrame.add(controlPanel);
		mainFrame.setSize(800, n*50);//TODO: set dynamicly
		mainFrame.setVisible(true);
	}
	
	private JPanel drawQuestion(Question question){
		JPanel questionPanel = new JPanel();
		JLabel questionId = new JLabel(question.getVariable().getIdentifier());
		JLabel questionString = new JLabel(question.getStr());
		JComponent input = getInputComponent(question);
		//TODO: dependent on the type of question an textbox or radio button etc..
		
		questionPanel.setLayout(new GridLayout(1,3));
		questionPanel.add(questionId);
		questionPanel.add(questionString);
		questionPanel.add(input);
		questionPanel.setVisible(true);
			
		return questionPanel;	
	}
	
	private JComponent getInputComponent(Question question){
		ql.ast.visitor.Type t = question.getVariable().getType().getType();
		if(t == ql.ast.visitor.Type.BOOLEAN){
			return new JRadioButton();
		}else{
			return new JTextField();
		}
	}
	
	private JPanel drawIfStatement(IfStatement ifStatement){
		JPanel ifStatementPanel = new JPanel();
		ifStatementPanel.setLayout(new GridLayout(ifStatement.getBody().getStatements().size(), 1));
		for(Statement s : ifStatement.getBody().getStatements()){
			if (s.getQuestion() != null){
				JPanel panel = drawQuestion(s.getQuestion());
				ifStatementPanel.add(panel);

			}
			if (s.getIfStatement() != null){
				JPanel panel = drawIfStatement(s.getIfStatement());
				ifStatementPanel.add(panel);
			}
		}
		ifStatementPanel.setVisible(true);
		return ifStatementPanel;
	}
}
