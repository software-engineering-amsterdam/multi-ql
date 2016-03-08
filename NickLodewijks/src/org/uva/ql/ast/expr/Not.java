package org.uva.ql.ast.expr;

import org.antlr.v4.runtime.ParserRuleContext;

public class Not extends UnaryExpr {

	public Not(ParserRuleContext context, Expr expr) {
		super(context, expr);
	}

	@Override
	public <T, U> T accept(ExprVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}
