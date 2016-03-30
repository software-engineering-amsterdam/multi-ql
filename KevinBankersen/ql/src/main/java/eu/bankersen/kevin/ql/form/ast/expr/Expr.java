package eu.bankersen.kevin.ql.form.ast.expr;

public abstract class Expr {

    private final int line;

    public Expr(int line) {
	this.line = line;
    }

    public int line() {
	return line;
    }

    public abstract <T, U> T accept(ExprVisitor<T, U> visitor, U context);

}