package uva.ql.ast.expressions.abstracts;

import java.util.Observable;
import java.util.Observer;

import uva.ql.ast.EnumType;
import uva.ql.ast.Node;
import uva.ql.visitors.INodeVisitor;

public abstract class Expression<T> extends Node implements Observer {
		
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
	public void update(Observable o, Object arg) {
		this.setChanged();
		this.notifyObservers(arg);
	}
}
