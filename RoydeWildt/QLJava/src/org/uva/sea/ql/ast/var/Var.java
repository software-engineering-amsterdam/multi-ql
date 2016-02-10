package org.uva.sea.ql.ast.var;

import org.uva.sea.ql.ast.Node;
import org.uva.sea.ql.ast.checker.Visitor;
import org.uva.sea.ql.ast.expr.Expr;

import java.util.List;

/**
 * Created by roydewildt on 04/02/16.
 */
public class Var extends Expr{
    private String value;
    public Var(String value) {this.value = value;}

    public String toString() {
        return this.getClass().getSimpleName() + "(" + value + ")";
    }
    public <T> T accept(Visitor visitor) {
        return visitor.visit(this);
    }

    public String getValue() {
        return value;
    }
}
