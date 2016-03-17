package org.uva.sea.ql.ast.tree.expr.unary;

import org.antlr.v4.runtime.Token;
import org.uva.sea.ql.ast.tree.atom.Literal;
import org.uva.sea.ql.ast.visitor.interfaces.ExprVisitor;

/**
 * Created by roy on 12-2-16.
 */
public class Primary extends UnaryExpr {

    private final Literal value;

    public Primary(Token token, Literal value) {
        super(token);
        this.value = value;
    }

    public Literal getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public String getSymbol() {
        return "";
    }

    @Override
    public <EXPR, CONTEXT> EXPR accept(ExprVisitor<EXPR, CONTEXT> visitor, CONTEXT context) {
        return visitor.visit(this,context);
    }
}