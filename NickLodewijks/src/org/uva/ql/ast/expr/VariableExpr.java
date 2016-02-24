package org.uva.ql.ast.expr;

import org.antlr.v4.runtime.ParserRuleContext;
import org.uva.ql.ast.ASTNodeVisitor;

public final class VariableExpr extends Expr {

	private final String variableId;

	public VariableExpr(ParserRuleContext context, String variableId) {
		super(context);
		this.variableId = variableId;
	}

	public String getVariableId() {
		return variableId;
	}

	@Override
	public Object interpret(Context context) {
		return context.getValue(variableId);
	}

	@Override
	public <T, U> T accept(ASTNodeVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}
