package org.uva.sea.ql.ast.expr.binary;

import org.uva.sea.ql.ast.Node;
import org.uva.sea.ql.ast.visitor.Visitor;
import org.uva.sea.ql.ast.expr.Expr;

import java.util.List;

/**
 * Created by roydewildt on 04/02/16.
 */
public class Div extends BinaryExpr {

    public Div(int line, Expr lhs, Expr rhs) {
        super(line, lhs, rhs);
    }
    public List<? extends Node> accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
