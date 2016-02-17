package org.uva.ql.ast.expr;

import org.antlr.v4.runtime.ParserRuleContext;
import org.uva.ql.ast.ASTNodeVisitor;

public class Pos extends Expr {

	private final Expr expr;

	public Pos(ParserRuleContext context, Expr expr) {
		super(context);
		this.expr = expr;
	}

	public Expr getExpr() {
		return expr;
	}

	@Override
	public Integer interpret(Context context) {
		return Math.abs((Integer) expr.interpret(context));
	}

	@Override
	public <T, U> T accept(ASTNodeVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}
