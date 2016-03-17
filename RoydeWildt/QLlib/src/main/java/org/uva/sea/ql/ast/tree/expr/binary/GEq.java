package org.uva.sea.ql.ast.tree.expr.binary;

import org.antlr.v4.runtime.Token;
import org.uva.sea.ql.ast.tree.expr.Expr;
import org.uva.sea.ql.ast.visitor.interfaces.ExprVisitor;

/**
 * Created by roydewildt on 04/02/16.
 */
public class GEq extends BinaryExpr {
    public GEq (Token token, Expr lhs, Expr rhs){
        super(token, lhs, rhs);
    }

    @Override
    public String getSymbol() {
        return ">=";
    }

    @Override
    public <EXPR, CONTEXT> EXPR accept(ExprVisitor<EXPR, CONTEXT> visitor, CONTEXT context) {
        return visitor.visit(this,context);
    }
}