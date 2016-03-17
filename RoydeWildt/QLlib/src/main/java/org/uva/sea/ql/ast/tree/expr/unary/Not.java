package org.uva.sea.ql.ast.tree.expr.unary;

import org.antlr.v4.runtime.Token;
import org.uva.sea.ql.ast.tree.expr.Expr;
import org.uva.sea.ql.ast.visitor.interfaces.ExprVisitor;

/**
 * Created by roydewildt on 04/02/16.
 */
public class Not extends UnaryExpr {
    private final Expr value;

    public Not (Token token, Expr lhs){
        super(token);
        this.value = lhs;
    }

    public Expr getValue() {
        return value;
    }

    @Override
    public String getSymbol() {
        return "!";
    }

    @Override
    public <EXPR, CONTEXT> EXPR accept(ExprVisitor<EXPR, CONTEXT> visitor, CONTEXT context) {
        return visitor.visit(this,context);
    }
}
