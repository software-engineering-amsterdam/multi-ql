package uva.ql.visitors;

import java.awt.Component;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JPanel;

import uva.ql.ast.Block;
import uva.ql.ast.EnumType;
import uva.ql.ast.Form;
import uva.ql.ast.conditionals.CondIfElseStatement;
import uva.ql.ast.conditionals.CondIfStatement;
import uva.ql.ast.expressions.abstracts.Expression;
import uva.ql.ast.expressions.abstracts.LogicalOperatorBinary;
import uva.ql.ast.questions.Question;
import uva.ql.gui.GUI;
import uva.ql.gui.visitors.IActionListenerVisitor;

public class VisitorActionListenersToGUI implements IActionListenerVisitor {
	
	private final Map<String, JComponent> componentStore = new HashMap<String, JComponent>(0);

	public VisitorActionListenersToGUI(GUI gui) {
	}
	
	@Override
	public void visitForm(Form form) {
		
		for(int i=0; i<form.size(); i++) {
			form.get(i).accept(this);
		}
	}

	@Override
	public void visitBlock(Block block) {
		
		for(int i=0; i<block.size(); i++) {
			block.get(i).accept(this);
		}
	}

	@Override
	public void visitCondIfStatement(CondIfStatement condition) {
		condition.getExpression().accept(this);
		condition.getLhs().accept(this);
	}

	@Override
	public void visitCondIfElseStatement(CondIfElseStatement condition) {
		condition.getExpression().accept(this);
		condition.getLhs().accept(this);
		condition.getRhs().accept(this);
	}
	
	@Override
	public void visitLogicalOperatorBinary(LogicalOperatorBinary exp) {
		System.out.println("Lhhs LogicalOperatorBinary: " + exp.getLhs().toString());
		System.out.println("Rhhs LogicalOperatorBinary: " + exp.getRhs().toString());
		exp.getLhs().accept(this);
		exp.getRhs().accept(this);
	}

}
