package sc.ql.ast.expr;

public final class VariableExpr extends Expr {

	private final String variableId;

	public VariableExpr(String variableId) {
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
