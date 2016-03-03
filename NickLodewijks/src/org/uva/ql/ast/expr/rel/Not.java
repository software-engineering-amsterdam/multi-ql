package org.uva.ql.ast.expr.rel;

import org.antlr.v4.runtime.ParserRuleContext;
import org.uva.ql.ast.expr.Expr;
import org.uva.ql.ast.expr.ExprVisitor;
import org.uva.ql.ast.expr.UnaryExpr;

public class Not extends UnaryExpr {

	public Not(ParserRuleContext context, Expr expr) {
		super(context, expr);
	}

	@Override
	public <T, U> T accept(ExprVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}
