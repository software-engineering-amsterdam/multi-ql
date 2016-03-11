package sc.ql.ast.expr;

public class Not extends UnaryExpr {

	public Not(Expr expr) {
		super(expr);
	}

	@Override
	public <T, U> T accept(ExprVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}
