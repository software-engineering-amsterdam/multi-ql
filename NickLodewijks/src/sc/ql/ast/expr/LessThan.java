package sc.ql.ast.expr;

public class LessThan extends BinaryExpr {

	public LessThan(Expr lhs, Expr rhs) {
		super(lhs, rhs);
	}

	@Override
	public <T, U> T accept(ExprVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}
