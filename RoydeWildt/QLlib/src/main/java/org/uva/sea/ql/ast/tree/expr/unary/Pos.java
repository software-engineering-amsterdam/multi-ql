package org.uva.sea.ql.ast.tree.expr.unary;

import org.uva.sea.ql.ast.tree.expr.Expr;
import org.uva.sea.ql.ast.visitor.interfaces.ExprVisitor;

/**
 * Created by roydewildt on 04/02/16.
 */
public class Pos extends UnaryExpr {
    private final Expr value;

    public Pos(int line, Expr lhs){
        super(line);
        this.value = lhs;
    }

    public Expr getValue() {
        return value;
    }

    @Override
    public String getSymbol() {
        return "+";
    }

    @Override
    public <EXPR, CONTEXT> EXPR accept(ExprVisitor<EXPR, CONTEXT> visitor, CONTEXT context) {
        return visitor.visit(this,context);
    }
}
