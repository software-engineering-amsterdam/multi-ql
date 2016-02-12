package org.uva.sea.ql.ast.tree.expr.binary;

import org.uva.sea.ql.ast.tree.Node;
import org.uva.sea.ql.ast.visitor.Visitor;
import org.uva.sea.ql.ast.tree.expr.Expr;

import java.util.List;

/**
 * Created by roydewildt on 04/02/16.
 */
public class GEq extends BinaryExpr {
    public GEq (int line, Expr lhs, Expr rhs){
        super(line, lhs, rhs);
    }
    public List<? extends Node> accept(Visitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public String getSymbol() {
        return ">=";
    }
}