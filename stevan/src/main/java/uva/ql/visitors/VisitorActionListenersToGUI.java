package uva.ql.visitors;

import javax.swing.JPanel;

import uva.ql.ast.Block;
import uva.ql.ast.Form;
import uva.ql.ast.conditionals.CondIfElseStatement;
import uva.ql.ast.conditionals.CondIfStatement;
import uva.ql.ast.expressions.abstracts.Expression;
import uva.ql.gui.GUI;
import uva.ql.gui.visitors.IActionListenerVisitor;

public class VisitorActionListenersToGUI implements IActionListenerVisitor {

	private final JPanel parentPanel;
	
	public VisitorActionListenersToGUI(GUI gui) {
		this.parentPanel = gui.getPanel();
	}
	
	@Override
	public void visitForm(Form form) {
		
		for(int i=0; i<form.size(); i++) {
			
			Block block = (Block) form.get(i);
			block.accept(this, (JPanel) parentPanel.getComponent(i));
		}
		
		//System.out.println(parentPanel.getComponentCount());
		
		parentPanel.revalidate();
		//System.out.println("Form - " + form.getName());
	}

	@Override
	public void visitBlock(Block block, JPanel panel) {
		
		System.out.println("BLOCK - " + panel.getComponentCount());
		for(int i=0; i<block.size(); i++) {
			System.out.println("\t" + panel.getComponent(i));
			block.get(i).accept(this, (JPanel) panel.getComponent(i));
		}
		//AbstractActionListener.enablePanel(containerPanel, false);
	}

	@Override
	public void visitCondIfStatement(CondIfStatement condition, JPanel panel) {
		//System.out.println(panel.getComponentCount());
		condition.getLhs().accept(this, panel);
	}

	@Override
	public void visitCondIfElseStatement(CondIfElseStatement condition, JPanel panel) {
		//System.out.println(panel.getComponentCount());
		condition.getExpression().accept(this, panel);
		condition.getLhs().accept(this, panel);
		condition.getRhs().accept(this, panel);
	}

	@Override
	public void visitExp(Expression exp, JPanel panel) {
		System.out.println(exp);
		System.out.println(exp.getType());
	}

}
