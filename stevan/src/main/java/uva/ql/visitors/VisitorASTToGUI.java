package uva.ql.visitors;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

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
import uva.ql.ast.values.ValueBool;
import uva.ql.ast.values.ValueInt;
import uva.ql.ast.values.ValueMoney;
import uva.ql.ast.variables.Variable;
import uva.ql.gui.Question;
import uva.ql.gui.fields.actionlisteners.JCheckBoxActionListener;
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
		
		//q.setPreferredSize(new Dimension(parentPanel.getWidth()-30, 20));
		parentPanel.setName(var.getName());
		parentPanel.add(q);
		parentPanel.revalidate();
		
		JComponent component = (JComponent) q.getComponent(1);
		componentStore.put(component.getName(), component);
	}

	@Override
	public void visitCondIfStatement(CondIfStatement condition, JPanel parentPanel) {
		
		JPanel panelLhs = new JPanel();
		panelLhs.setLayout(new BoxLayout(panelLhs, BoxLayout.PAGE_AXIS));
		
		condition.getLhs().accept(this, panelLhs);
		parentPanel.setName("condition");
		parentPanel.add(panelLhs);
		parentPanel.revalidate();
		
		//System.out.println("condition: " + condition.getExpression());
		//System.out.println("componentStore: " + componentStore.entrySet());
		condition.getExpression().accept(this, panelLhs);
		
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
		
		//condition.getExpression().accept(this, panelLhs);
		//System.out.println(condition.getExpression());
	}
	
	@Override
	public void visitVariables(Variable var) {
		//System.out.println(var.getName());
	}
	
	@Override
	public void visitVarInt(Variable var, JPanel panel) {
		//System.out.println("Int: " + var.getName());
	}

	@Override
	public void visitVarMoney(Variable var, JPanel panel) {
		//System.out.println("Money: " + var.getName());
	}
	
	@Override
	public void visitVarBool(Variable var, JPanel panel) {
		//System.out.println("Bool: " + var.getName());
		JCheckBox checkBox = (JCheckBox) componentStore.get(var.getName());
		//checkBox.addActionListener(new JCheckBoxActionListener(checkBox, exp, panel));
	}

	@Override
	public void visitValueBool(ValueBool val, JPanel panel) {
		//System.out.println(val.getType() + ": " + val.getValue());
	}
	
	@Override
	public void visitValueInt(ValueInt val, JPanel panel) {
		//System.out.println(val.getType() + ": " + val.getValue());
	}
	
	@Override
	public void visitValueMoney(ValueMoney val, JPanel panel) {
		//System.out.println(val.getType() + ": " + val.getValue());
	}

	@Override
	public void visitArithmeticOperator(ArithmeticOperatorBinary exp, JPanel panel) {
		System.out.println("Lhs ArithmeticOperatorBinary: " + exp.getLhs().toString());
		System.out.println("Rhs ArithmeticOperatorBinary: " + exp.getRhs().toString());
		exp.getLhs().accept(this, panel);
		exp.getRhs().accept(this, panel);
	}

	@Override
	public void visitLogicalOperatorBinary(LogicalOperatorBinary exp, JPanel panel) {
		//System.out.println("Lhs LogicalOperatorBinary: " + exp.getLhs().toString());
		//System.out.println("Rhs LogicalOperatorBinary: " + exp.getRhs().toString());
		exp.getLhs().accept(this, panel);
		exp.getRhs().accept(this, panel);
	}
	
	@Override
	public void visitLogicalOperatorUnary(LogicalOperatorUnary exp, JPanel panel) {
		System.out.println("Lhs LogicalOperatorUnary: " + exp.getLhs().toString());
		exp.getLhs().accept(this, panel);
	}
	
	@Override
	public void visitRelationalOperatorBinary(RelationalOperatorBinary exp, JPanel panel) {
		System.out.println("Lhs RelationalOperatorBinary: " + exp.getLhs().toString());
		System.out.println("Rhs RelationalOperatorBinary: " + exp.getRhs().toString());
		exp.getLhs().accept(this, panel);
		exp.getRhs().accept(this, panel);
	}
}
