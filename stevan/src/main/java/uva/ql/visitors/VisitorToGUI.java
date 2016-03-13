package uva.ql.visitors;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;

import uva.ql.ast.Block;
import uva.ql.ast.Form;
import uva.ql.ast.conditionals.CondIfElseStatement;
import uva.ql.ast.conditionals.CondIfStatement;
import uva.ql.ast.questions.QuestionComputed;
import uva.ql.ast.questions.QuestionVanilla;
import uva.ql.ast.values.abstracts.Values;
import uva.ql.ast.variables.abstracts.Variable;
import uva.ql.gui.GUI;
import uva.ql.gui.Question;
import uva.ql.gui.visitors.IGUIVisitor;

public class VisitorToGUI implements IGUIVisitor {

	private final JPanel parentPanel;
	
	public VisitorToGUI(GUI gui) {
		this.parentPanel = gui.getPanel();
	}
	
	@Override
	public void visitForm(Form form) {
		
		for(int i=0; i<form.size(); i++) {
			
			Block block = (Block) form.get(i);
			parentPanel.add(block.accept(this));
		}
		
		parentPanel.revalidate();
		//System.out.println("Form - " + form.getName());
	}

	@Override
	public JComponent visitBlock(Block block) {
		
		JPanel containerPanel = new JPanel();
		containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.PAGE_AXIS));
		
		for(int i=0; i<block.size(); i++) {
			block.get(i).accept(this, containerPanel);
		}
		
		return containerPanel;
	}

	@Override
	public void visitQuestionVanilla(QuestionVanilla question, JPanel panel) {
		Variable var = question.getVariable();
		var.accept(this);
		Question q = new Question(question.getLabel(), var);
		q.setPreferredSize(new Dimension(parentPanel.getWidth()-30, 20));
		panel.add(q);
	}

	@Override
	public void visitQuestionComputed(QuestionComputed question, JPanel panel) {
		Variable var = question.getVariable();
		Question q = new Question(question.getLabel(), var);
		q.setPreferredSize(new Dimension(parentPanel.getWidth()-30, 20));
		panel.add(q);
	}

	@Override
	public void visitCondIfStatement(CondIfStatement condition, JPanel panel) {
		panel.add(condition.getLhs().accept(this));
	}

	@Override
	public void visitCondIfElseStatement(CondIfElseStatement condition, JPanel panel) {
		panel.add(condition.getLhs().accept(this));
		panel.add(condition.getRhs().accept(this));
	}

	@Override
	public void visitVar(Variable variable) {
		//System.out.println(variable.getName() + " - " + variable.getType());
	}

	@Override
	public void visitNum(Values values) {
		// TODO Auto-generated method stub
		
	}

}
