package eu.bankersen.kevin.ql.ast.expr;

import eu.bankersen.kevin.ql.ast.values.QLValue;
import eu.bankersen.kevin.ql.interpreter.Environment;

public abstract class Expr {

    private final int line;

    public Expr(int line) {
	this.line = line;
    }

    public int line() {
	return line;
    }

    public abstract QLValue eval(Environment environment);

    public abstract <T, U> T accept(ExprVisitor<T, U> visitor, U context);

}