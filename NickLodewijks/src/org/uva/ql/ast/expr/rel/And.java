package org.uva.ql.ast.expr.rel;

import org.antlr.v4.runtime.ParserRuleContext;
import org.uva.ql.ast.expr.BinaryExpr;
import org.uva.ql.ast.expr.Expr;
import org.uva.ql.ast.expr.ExprVisitor;

public class And extends BinaryExpr {

	public And(ParserRuleContext context, Expr lhs, Expr rhs) {
		super(context, lhs, rhs);
	}

	@Override
	public <T, U> T accept(ExprVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}
