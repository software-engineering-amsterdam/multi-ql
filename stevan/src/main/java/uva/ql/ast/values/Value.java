package uva.ql.ast.values;

import uva.ql.ast.Node;
import uva.ql.ast.expressions.abstracts.Expression;

public abstract class Value<T> extends Expression<T> {

	public Value(Node parent, int startLine, int startColumn) {
		super(parent, startLine, startColumn);
	}
	
	public abstract T getValue();
}
