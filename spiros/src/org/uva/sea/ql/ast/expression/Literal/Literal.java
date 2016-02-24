package org.uva.sea.ql.ast.expression.Literal;

import org.uva.sea.ql.ast.expression.Expression;
import org.uva.sea.ql.ast.expression.ExpressionVisitor;
import org.uva.sea.ql.ast.node.CodeFragment;

public abstract class Literal extends Expression {
	
	public Literal(CodeFragment fragment) {
		super(fragment);
	}
	
	public abstract <T> T accept(ExpressionVisitor<T> visitor);

}