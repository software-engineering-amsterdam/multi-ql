package org.uva.sea.ql.ast.expr.unary;

import org.uva.sea.ql.ast.visitor.Visitor;
import org.uva.sea.ql.ast.expr.Expr;

/**
 * Created by roydewildt on 04/02/16.
 */
public class Pos extends UnaryExpr {

    public Pos(int line, Expr lhs){
        super(line, lhs);
    }
    public <T> T accept(Visitor visitor) {
        return visitor.visit(this);
    }

}
