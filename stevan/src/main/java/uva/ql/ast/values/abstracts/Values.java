package uva.ql.ast.values.abstracts;

import uva.ql.ast.abstracts.Node;
import uva.ql.ast.expressions.abstracts.Expression;

public abstract class Values<T> extends Expression {

	public Values(Node parent, int startLine, int startColumn) {
		super(parent, startLine, startColumn);
	}
	
	public abstract T getValue();
}
