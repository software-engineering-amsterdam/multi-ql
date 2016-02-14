package eu.bankersen.kevin.ql.ast.expr;

import eu.bankersen.kevin.ql.ast.Type;

public abstract class Expr {

    public abstract Object result();

    public abstract void checkType();

    public abstract Type getType();

}