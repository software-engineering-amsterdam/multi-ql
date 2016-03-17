package org.uva.sea.ql.ast.tree.expr;


import org.antlr.v4.runtime.Token;
import org.uva.sea.ql.ast.tree.Node;
import org.uva.sea.ql.ast.visitor.interfaces.ExprVisitable;

/**
 * Created by roydewildt on 04/02/16.
 */
abstract public class Expr extends Node implements ExprVisitable {

    public abstract String getSymbol();
    public Expr(Token token) {
        super(token);
    }
}
