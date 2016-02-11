package org.uva.ql.ast.expr;

import org.uva.ql.ast.ASTNodeVisitor;
import org.uva.ql.ast.literal.Literal;

public class LiteralExpr extends Expr {

	private Literal<?> literal;

	public LiteralExpr(Literal<?> literal) {
		this.literal = literal;
	}

	public Literal<?> getLiteral() {
		return literal;
	}

	@Override
	public Object interpret(Context context) {
		return literal.getValue();
	}

	@Override
	public <T, U> T accept(ASTNodeVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}
