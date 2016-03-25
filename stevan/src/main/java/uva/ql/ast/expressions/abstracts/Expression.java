package uva.ql.ast.expressions.abstracts;

import javax.swing.JPanel;

import uva.ql.ast.EnumType;
import uva.ql.ast.Node;
import uva.ql.gui.visitors.IActionListenerVisitor;
import uva.ql.visitors.INodeVisitor;

public abstract class Expression<T> extends Node{
		
	public Expression(Node parent, int startLine, int startColumn) {
		super(parent, startLine, startColumn);
	}
	
	abstract public EnumType evalType();

	abstract public EnumType getType();
	
	abstract public T eval();

	@Override
	public void accept(INodeVisitor visitor) {
		visitor.visitExp(this);
	}
	
	@Override
	public void accept(IActionListenerVisitor visitor, JPanel panel) {
		visitor.visitExp(this, panel);
	}
}
