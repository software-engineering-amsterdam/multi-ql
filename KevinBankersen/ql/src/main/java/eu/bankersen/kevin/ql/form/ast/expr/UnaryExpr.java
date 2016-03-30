package eu.bankersen.kevin.ql.ast.expr;

public abstract class UnaryExpr extends Expr {

    private final Expr expr;

    public UnaryExpr(int line, Expr expr) {
	super(line);
	this.expr = expr;
    }

    public Expr expr() {
	return expr;
    }
}
