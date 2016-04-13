package uva.ql.ast.expression;

import java.util.Observable;
import java.util.Observer;

import uva.ql.ast.Node;
import uva.ql.visitors.INodeVisitor;

public abstract class Expression extends Node implements Observer {
		
	public Expression(Node parent, int startLine, int startColumn) {
		super(parent, startLine, startColumn);
	}
	
	@Override
	public void accept(INodeVisitor visitor) {
		visitor.visitExp(this);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		this.setChanged();
		this.notifyObservers(arg);
	}
}
