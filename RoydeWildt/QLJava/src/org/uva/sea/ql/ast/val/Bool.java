package org.uva.sea.ql.ast.val;

import org.uva.sea.ql.ast.Node;
import org.uva.sea.ql.ast.checker.Visitor;

import java.util.List;

/**
 * Created by roydewildt on 04/02/16.
 */
public class Bool extends Val {
    Boolean value;

    public Bool () {}
    public Bool(String x){
        this.value = Boolean.valueOf(x);
    }

    public List<? extends Node> accept(Visitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "(" + value + ")";
    }
}
