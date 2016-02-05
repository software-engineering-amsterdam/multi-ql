package org.uva.sea.ql.ast.expr;


import org.uva.sea.ql.ast.Node;
import org.uva.sea.ql.ast.checker.Visitable;
import org.uva.sea.ql.ast.checker.Visitor;

import java.util.List;

/**
 * Created by roydewildt on 04/02/16.
 */
abstract public class Expr implements Node, Visitable {
    private Expr lhs;
    private Expr rhs;

    public Expr(){}

    //for unary expressions
    public Expr(Expr lhs) {
        this.lhs = lhs;
    }

    //for binary expressions
    public Expr(Expr lhs, Expr rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public String toString() {
        if(rhs == null)
            return this.getClass().getSimpleName() + "(" + lhs.toString() + ")";
        else
            return this.getClass().getSimpleName() + "(" + lhs.toString() + ", " + rhs.toString() + ")";
    }

    public List<String> accept(Visitor visitor) {
        return visitor.visit(this);
    }

    public Expr getLhs() {
        return lhs;
    }

    public Expr getRhs() {
        return rhs;
    }
}
