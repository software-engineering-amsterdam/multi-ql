package org.uva.sea.ql.ast.tree.expr.binary;

import org.uva.sea.ql.ast.visitor.Visitor;
import org.uva.sea.ql.ast.tree.expr.Expr;

/**
 * Created by roydewildt on 04/02/16.
 */
public class LT extends BinaryExpr {
    public LT (int line, Expr lhs, Expr rhs){
        super(line, lhs, rhs);
    }
    public <T,U> T accept(Visitor<T,U> visitor, U context) {
        return visitor.visit(this, context);
    }

    @Override
    public String getSymbol() {
        return ">";
    }
}
