package org.uva.ql.ast.stat;

import org.uva.ql.ast.ASTSourceInfo;
import org.uva.ql.ast.expr.Expr;
import org.uva.ql.ast.form.QLBlock;

public final class QLIFStatement extends QLStatement {

	private final Expr expr;
	private final QLBlock body;

	public QLIFStatement(ASTSourceInfo context, Expr condition, QLBlock body) {
		super(context);
		this.expr = condition;
		this.body = body;
	}

	public Expr getExpr() {
		return expr;
	}

	public QLBlock getBody() {
		return body;
	}

	@Override
	public <T, U> T accept(QLStatementVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}
