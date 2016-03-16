package uva.ql.ast.values.abstracts;

import uva.ql.ast.abstracts.Node;
import uva.ql.ast.expressions.abstracts.Expression;

public abstract class Value<T> extends Expression {

	public Value(Node parent, int startLine, int startColumn) {
		super(parent, startLine, startColumn);
	}
	
	public abstract T getValue();
}
