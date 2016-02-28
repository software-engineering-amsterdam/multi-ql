package org.uva.sea.ql.ast.tree.expr.unary;

import org.uva.sea.ql.ast.tree.expr.Expr;
import org.uva.sea.ql.ast.visitor.interfaces.IExprVisitor;

/**
 * Created by roydewildt on 04/02/16.
 */
public class Pos extends UnaryExpr {
    Expr value;

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
    public <E, C> E accept(IExprVisitor<E, C> visitor, C context) {
        return visitor.visit(this,context);
    }
}
