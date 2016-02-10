package org.uva.sea.ql.ast.val;

import org.uva.sea.ql.ast.Node;
import org.uva.sea.ql.ast.checker.Visitor;

import java.util.List;

/**
 * Created by roydewildt on 04/02/16.
 */
public class Int extends Val {
    int value;

    public Int(){}
    public Int(String x){
        Integer.valueOf(x);
    }

    public <T> T accept(Visitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "(" + value + ")";
    }
}
