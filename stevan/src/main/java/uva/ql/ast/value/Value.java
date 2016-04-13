package uva.ql.ast.value;

import uva.ql.ast.Node;
import uva.ql.ast.expression.Expression;


public abstract class Value extends Expression {

	private final String value;
	
	public Value(Node parent, String value, int startLine, int startColumn) {
		super(parent, startLine, startColumn);
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
