package eu.bankersen.kevin.ql.ast.expr;

import eu.bankersen.kevin.ql.ast.Type;

public abstract class StringExpr extends Expr {

    private final Type type = Type.STRING;

    public abstract String result();

    @Override
    public final Type getType() {
	return type;
    }
}
