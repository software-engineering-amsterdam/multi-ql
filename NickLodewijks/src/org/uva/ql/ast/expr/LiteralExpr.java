package org.uva.ql.ast.expr;

import org.antlr.v4.runtime.ParserRuleContext;
import org.uva.ql.ast.ASTNodeVisitor;
import org.uva.ql.ast.literal.Literal;

public final class LiteralExpr extends Expr {

	private final Literal<?> literal;

	public LiteralExpr(ParserRuleContext context, Literal<?> literal) {
		super(context);
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
