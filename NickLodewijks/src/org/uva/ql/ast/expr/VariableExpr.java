package org.uva.ql.ast.expr;

import org.uva.ql.ast.ASTSourceInfo;

public final class VariableExpr extends Expr {

	private final String variableId;

	public VariableExpr(ASTSourceInfo context, String variableId) {
		super(context);
		this.variableId = variableId;
	}

	public String getVariableId() {
		return variableId;
	}

	@Override
	public <T, U> T accept(ExprVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}
