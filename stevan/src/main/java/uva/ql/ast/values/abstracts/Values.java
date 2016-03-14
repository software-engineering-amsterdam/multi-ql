package uva.ql.ast.values.abstracts;

import uva.ql.ast.abstracts.Node;
import uva.ql.ast.expressions.abstracts.Expression;

public abstract class Values extends Expression {

	public Values(Node parent, int startLine, int startColumn) {
		super(parent, startLine, startColumn);
	}
}
