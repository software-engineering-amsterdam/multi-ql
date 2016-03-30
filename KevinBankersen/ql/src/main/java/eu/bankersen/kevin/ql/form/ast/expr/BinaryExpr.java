package eu.bankersen.kevin.ql.form.ast.expr;

public abstract class BinaryExpr extends Expr {

    private final Expr lhs;
    private final Expr rhs;

    public BinaryExpr(int line, Expr lhs, Expr rhs) {
	super(line);
	this.lhs = lhs;
	this.rhs = rhs;
    }

    public Expr lhs() {
	return lhs;
    }

    public Expr rhs() {
	return rhs;
    }
}
