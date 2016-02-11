package org.uva.sea.ql.ast.expr.binary;

import org.uva.sea.ql.ast.visitor.Visitor;
import org.uva.sea.ql.ast.expr.Expr;

/**
 * Created by roydewildt on 04/02/16.
 */
public class Sub extends BinaryExpr {

    public Sub(int line, Expr lhs, Expr rhs) {
        super(line, lhs, rhs);
    }
    public <T> T accept(Visitor visitor) {
        return visitor.visit(this);
    }


}
