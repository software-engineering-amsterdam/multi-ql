package uva.ql.visitors;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPanel;

import uva.ql.ast.Block;
import uva.ql.ast.Form;
import uva.ql.ast.conditionals.CondIfElseStatement;
import uva.ql.ast.conditionals.CondIfStatement;
import uva.ql.ast.expressions.abstracts.ArithmeticOperatorBinary;
import uva.ql.ast.expressions.abstracts.Expression;
import uva.ql.ast.expressions.abstracts.LogicalOperatorBinary;
import uva.ql.ast.expressions.abstracts.LogicalOperatorUnary;
import uva.ql.ast.expressions.abstracts.RelationalOperatorBinary;
import uva.ql.ast.questions.QuestionComputed;
import uva.ql.ast.questions.QuestionVanilla;
import uva.ql.ast.variables.Variable;
import uva.ql.gui.Question;
import uva.ql.gui.fields.actionlisteners.CheckBoxActionListener;
import uva.ql.gui.observers.ComputedQuestionObserver;
import uva.ql.gui.observers.DoublePanelObserver;
import uva.ql.gui.observers.SinglePanelObserver;

public class VisitorASTToGUI implements IGUIVisitor {

	private final Map<String, JComponent> componentStore = new HashMap<String, JComponent>(0);
	private final Map<String, Variable> variableStore = new HashMap<String, Variable>(0);
	
	@Override
	public void visitForm(Form form, JPanel parentPanel) {
		
		for(int i=0; i<form.size(); i++) {
			
			JPanel panel = new JPanel();
			panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
			panel.setPreferredSize(new Dimension(parentPanel.getParent().getWidth()-30, parentPanel.getParent().getHeight()-30));

			parentPanel.add(panel);
			parentPanel.revalidate();
			
			form.get(i).accept(this, panel);
		}
	}

	@Override
	public void visitBlock(Block block, JPanel parentPanel) {
		
		for(int i=0; i<block.size(); i++) {
			
			JPanel panel = new JPanel();
			panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
			
			parentPanel.add(panel);
			parentPanel.revalidate();
			
			block.get(i).accept(this, panel);
		}
	}

	@Override
	public void visitQuestionVanilla(QuestionVanilla question, JPanel parentPanel) {
		Variable var = question.getVariable();
		Question q = new Question(question.getLabel(), var);
		
		parentPanel.setName(var.getName());
		parentPanel.add(q);
		parentPanel.revalidate();
		
		JComponent component = (JComponent) q.getComponent(1);
		componentStore.put(component.getName(), component);
	}

	@Override
	public void visitQuestionComputed(QuestionComputed question, JPanel parentPanel) {
		Variable var = question.getVariable();
		Question q = new Question(question.getLabel(), var);
		Expression exp = question.getExp();
		exp.accept(this, parentPanel);
		
		parentPanel.setName(var.getName());
		parentPanel.add(q);
		parentPanel.revalidate();
		
		JComponent component = (JComponent) q.getComponent(1);
		componentStore.put(component.getName(), component);
		
		ComputedQuestionObserver computedQuestionObserver = new ComputedQuestionObserver(component, exp);
		exp.addObserver(computedQuestionObserver);
		
		for( Entry<String, Variable> varInStore : variableStore.entrySet() ) {
			varInStore.getValue().addObserver(exp);
		}
	}

	@Override
	public void visitCondIfStatement(CondIfStatement condition, JPanel parentPanel) {
		
		JPanel panelLhs = new JPanel();
		panelLhs.setLayout(new BoxLayout(panelLhs, BoxLayout.PAGE_AXIS));
		Expression<Boolean> exp = condition.getExpression();
		
		condition.getLhs().accept(this, panelLhs);
		condition.getExpression().accept(this, panelLhs);
		
		SinglePanelObserver singlePanelObserver = new SinglePanelObserver(panelLhs, exp);
		exp.addObserver(singlePanelObserver);
		
		parentPanel.add(panelLhs);
		parentPanel.revalidate();
		
		for( Entry<String, Variable> var : variableStore.entrySet() ) {
			var.getValue().addObserver(exp);
		}
	}

	@Override
	public void visitCondIfElseStatement(CondIfElseStatement condition, JPanel parentPanel) {
		
		JPanel panelLhs = new JPanel();
		panelLhs.setLayout(new BoxLayout(panelLhs, BoxLayout.PAGE_AXIS));
		condition.getLhs().accept(this, panelLhs);
		parentPanel.add(panelLhs);
		
		JPanel panelRhs = new JPanel();
		panelRhs.setLayout(new BoxLayout(panelRhs, BoxLayout.PAGE_AXIS));
		condition.getRhs().accept(this, panelRhs);
		parentPanel.add(panelRhs);
		
		parentPanel.revalidate();
		
		condition.getExpression().accept(this, panelLhs);
		condition.getExpression().accept(this, panelRhs);
				
		Expression<Boolean> exp = condition.getExpression();
		
		for( Entry<String, Variable> var : variableStore.entrySet() ) {
			var.getValue().addObserver(exp);
		}
		
		DoublePanelObserver doublePanelObserver = new DoublePanelObserver(panelLhs, panelRhs, exp);
		
		exp.addObserver(doublePanelObserver);
	}
	
	@Override
	public void visitVarDate(Variable<String> var, JPanel panel) {
		variableStore.put(var.getName(), var);
	}
	
	@Override
	public void visitVarMoney(Variable<Integer> var, JPanel panel) {
		variableStore.put(var.getName(), var);
	}
	
	@Override
	public void visitVarInt(Variable<Integer> var, JPanel panel) {
		variableStore.put(var.getName(), var);
	}
	
	@Override
	public void visitVarBool(Variable<Boolean> var, JPanel panel) {
		JCheckBox checkBox = (JCheckBox) componentStore.get(var.getName());
		variableStore.put(var.getName(), var);
		checkBox.addActionListener(new CheckBoxActionListener(checkBox, var));
	}

	@Override
	public void visitLogicalOperatorBinary(LogicalOperatorBinary exp, JPanel panel) {
		exp.getLhs().accept(this, panel);
		exp.getRhs().accept(this, panel);
	}
	
	@Override
	public void visitLogicalOperatorUnary(LogicalOperatorUnary exp, JPanel panel) {
		exp.getLhs().accept(this, panel);
	}

	@Override
	public void visitArithmeticOperator(ArithmeticOperatorBinary exp,
			JPanel panel) {
		exp.getLhs().accept(this, panel);
		exp.getRhs().accept(this, panel);
	}

	@Override
	public void visitRelationalOperatorBinary(RelationalOperatorBinary exp,
			JPanel panel) {
		exp.getLhs().accept(this, panel);
		exp.getRhs().accept(this, panel);
	}
	
}
