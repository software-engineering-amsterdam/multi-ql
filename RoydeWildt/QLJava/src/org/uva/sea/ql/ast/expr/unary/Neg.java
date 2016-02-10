package org.uva.sea.ql.ast.expr.unary;

import org.uva.sea.ql.ast.Node;
import org.uva.sea.ql.ast.checker.Visitor;
import org.uva.sea.ql.ast.expr.Expr;

import java.util.List;

/**
 * Created by roydewildt on 04/02/16.
 */
public class Neg extends UnaryExpr {
    public Neg(Expr lhs){
        super(lhs);
    }
    public <T> T accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
