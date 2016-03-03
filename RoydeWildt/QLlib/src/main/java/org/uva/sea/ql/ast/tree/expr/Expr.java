package org.uva.sea.ql.ast.tree.expr;


import org.uva.sea.ql.ast.tree.Node;
import org.uva.sea.ql.ast.visitor.interfaces.ExprVisitable;

/**
 * Created by roydewildt on 04/02/16.
 */
abstract public class Expr extends Node implements ExprVisitable {

    public abstract String getSymbol();
    public Expr(int line) {
        super(line);
    }
}
