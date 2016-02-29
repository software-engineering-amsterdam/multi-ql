package eu.bankersen.kevin.ql.ast.expr;

import eu.bankersen.kevin.ql.ast.Type;

public abstract class StringExpr extends Expr {

    public StringExpr() {
	super(Type.STRING);
    }

    public abstract String eval();
}
