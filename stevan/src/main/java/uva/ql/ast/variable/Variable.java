package uva.ql.ast.variable;

import java.util.Observable;

import uva.ql.ast.Node;
import uva.ql.ast.expression.Expression;

public abstract class Variable extends Expression {

	private final String name;
	
	public Variable(Node parent, String name, int startLine, int startColumn) {
		super(parent, startLine, startColumn);
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	@Override
	public void update(Observable o, Object arg) {}
}
