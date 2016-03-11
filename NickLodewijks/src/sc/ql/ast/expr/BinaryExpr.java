package sc.ql.ast.expr;

public abstract class BinaryExpr extends Expr {

	private final Expr lhs;
	private final Expr rhs;

	public BinaryExpr(Expr lhs, Expr rhs) {
		this.lhs = lhs;
		this.rhs = rhs;
	}

	public final Expr left() {
		return lhs;
	}

	public final Expr right() {
		return rhs;
	}
}
