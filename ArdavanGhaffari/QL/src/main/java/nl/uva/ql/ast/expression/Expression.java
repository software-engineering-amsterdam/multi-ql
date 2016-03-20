package nl.uva.ql.ast.expression;

import nl.uva.ql.ast.AbstractNode;
import nl.uva.ql.visitors.ExpressionVisitor;

public abstract class Expression extends AbstractNode {
	public Expression(int line) {
		super(line);
	}

	public abstract <T> T accept(ExpressionVisitor<T> expressionVisitor);
}
