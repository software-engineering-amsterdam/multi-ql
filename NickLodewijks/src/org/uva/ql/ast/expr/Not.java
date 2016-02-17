package org.uva.ql.ast.expr;

import org.antlr.v4.runtime.ParserRuleContext;
import org.uva.ql.ast.ASTNodeVisitor;

public class Not extends Expr {

	private final Expr expr;

	public Not(ParserRuleContext context, Expr expr) {
		super(context);
		this.expr = expr;
	}

	@Override
	public Boolean interpret(Context context) {
		return !(Boolean) expr.interpret(context);
	}

	@Override
	public <T, U> T accept(ASTNodeVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}

	public Expr getExpr() {
		return expr;
	}
}
