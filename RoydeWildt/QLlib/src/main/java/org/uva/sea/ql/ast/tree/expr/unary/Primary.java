package org.uva.sea.ql.ast.tree.expr.unary;

import org.uva.sea.ql.ast.tree.atom.Literal;
import org.uva.sea.ql.ast.visitor.interfaces.ExprVisitor;

/**
 * Created by roy on 12-2-16.
 */
public class Primary extends UnaryExpr {

    private final Literal value;

    public Primary (Literal value){
        super(0);
        this.value = value;
    }

    public Primary(int line, Literal value) {
        super(line);
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