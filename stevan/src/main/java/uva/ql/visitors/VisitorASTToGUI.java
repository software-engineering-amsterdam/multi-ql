package uva.ql.visitors;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;

import uva.ql.ast.Block;
import uva.ql.ast.Form;
import uva.ql.ast.conditionals.CondIfElseStatement;
import uva.ql.ast.conditionals.CondIfStatement;
import uva.ql.ast.expressions.abstracts.ArithmeticOperatorBinary;
import uva.ql.ast.expressions.abstracts.LogicalOperatorBinary;
import uva.ql.ast.expressions.abstracts.LogicalOperatorUnary;
import uva.ql.ast.expressions.abstracts.RelationalOperatorBinary;
import uva.ql.ast.questions.QuestionComputed;
import uva.ql.ast.questions.QuestionVanilla;
import uva.ql.ast.values.abstracts.Value;
import uva.ql.ast.variables.abstracts.Variable;
import uva.ql.gui.Question;
import uva.ql.gui.visitors.IGUIVisitor;

public class VisitorASTToGUI implements IGUIVisitor {

	private final Map<String, JComponent> componentStore = new HashMap<String, JComponent>(0);
	
	public VisitorASTToGUI() {
	}
	
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
		
		//q.setPreferredSize(new Dimension(parentPanel.getWidth()-30, 20));
		parentPanel.add(q);
		parentPanel.revalidate();
		
		JComponent component = (JComponent) q.getComponent(1);
		componentStore.put(component.getName(), component);
	}

	@Override
	public void visitQuestionComputed(QuestionComputed question, JPanel parentPanel) {
		Variable var = question.getVariable();
		Question q = new Question(question.getLabel(), var);
		
		//q.setPreferredSize(new Dimension(parentPanel.getWidth()-30, 20));
		parentPanel.add(q);
		parentPanel.revalidate();
		
		JComponent component = (JComponent) q.getComponent(1);
		componentStore.put(component.getName(), component);
	}

	@Override
	public void visitCondIfStatement(CondIfStatement condition, JPanel parentPanel) {
		
		JPanel panelLhs = new JPanel();
		panelLhs.setLayout(new BoxLayout(panelLhs, BoxLayout.PAGE_AXIS));
		
		/* 
		 * Add an ItemListener or an ActionListener?
		 * 
		 * For an ActionListener I need the actual JComponent like a CheckBox, and pass the JPanel to it,
		 * so as to be able to trigger the JPanel to show/hide upon the performed action of the CheckBox...
		 *
		 * - How to get the CheckBox at this point in time?
		 *   Perhaps I should store all the JComponents that are associated to Questions in a HashMap as
		 *   HashMap<String, JComponent>, aka HashMap<varName, CheckBox/TextField/Date>
		 *   
		 * - From here on I should get the expression, evaluate it and if it's true it should show the 
		 *   JPanel...
		 * 
		 * - If it's a unary expression (true) then the ActionListener will trigger to show the JPanel
		 *   
		 * - If it's a binary expression (true AND false) then the lhs will trigger the JPanel to show and
		 *   the rhs will trigger the JPanel to hide.
		 *   
		 * - If it's a nested binary expression (!true AND (true OR false)) then the expression needs to 
		 *   trigger the JPanel to show/hide
		 *   
		 * - So I need the ActionListener to trigger the expression evaluator. Which means that the 
		 *   expression evaluator needs to gather the values of the expression.
		 *   
		 *   		public boolean expression.eval(Expression[] args) {} (Expression/Variables/Values)
		 *   
		 * - So I need a JCheckBox, the evaluated Expression and the JPanel to be triggered
		 * 
		 *			private void showHideJPanel(JCheckBox checkbox, boolean evalExp, JPanel panel);
		 * 
		 * - Then the actual ActionListener to attach to the CheckBox/TextField/MoneyField
		 * 
		 * 			
		 * 
		 */
		
		condition.getLhs().accept(this, panelLhs);
		parentPanel.add(panelLhs);
		parentPanel.revalidate();
		
		condition.getExpression().accept(this, panelLhs);
		System.out.println(condition.getExpression());
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
		System.out.println(condition.getExpression());
	}
	
	@Override
	public void visitVariables(Variable var) {
		System.out.println(var.getName());
	}

	@Override
	public void visitValueBool(Value val, JPanel panel) {
		System.out.println(val.getValue() + " - " + val);
	}

	@Override
	public void visitArithmeticOperator(ArithmeticOperatorBinary exp, JPanel panel) {
		System.out.println(exp.getLhs().toString());
		System.out.println(exp.getRhs().toString());
		exp.getLhs().accept(this, panel);
		exp.getRhs().accept(this, panel);
	}

	@Override
	public void visitLogicalOperatorBinary(LogicalOperatorBinary exp, JPanel panel) {
		System.out.println(exp.getLhs().toString());
		System.out.println(exp.getRhs().toString());
		exp.getLhs().accept(this, panel);
		exp.getRhs().accept(this, panel);
	}
	
	@Override
	public void visitLogicalOperatorUnary(LogicalOperatorUnary exp, JPanel panel) {
		System.out.println(exp.getLhs().toString());
		exp.getLhs().accept(this, panel);
	}
	
	@Override
	public void visitRelationalOperatorBinary(RelationalOperatorBinary exp, JPanel panel) {
		System.out.println(exp.getLhs().toString());
		System.out.println(exp.getRhs().toString());
		exp.getLhs().accept(this, panel);
		exp.getRhs().accept(this, panel);
	}
}
