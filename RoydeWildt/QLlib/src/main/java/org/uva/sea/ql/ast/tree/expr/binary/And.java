package org.uva.sea.ql.ast.tree.expr.binary;

import org.uva.sea.ql.ast.tree.expr.Expr;
import org.uva.sea.ql.ast.visitor.interfaces.*;

/**
 * Created by roydewildt on 04/02/16.
 */
public class And extends BinaryExpr {
    public And (int line, Expr lhs, Expr rhs){
        super(line, lhs, rhs);
    }

    @Override
    public String getSymbol() {
        return "&&";
    }

    @Override
    public <EXPR, CONTEXT> EXPR accept(ExprVisitor<EXPR, CONTEXT> visitor, CONTEXT context) {
        return visitor.visit(this,context);
    }
}
