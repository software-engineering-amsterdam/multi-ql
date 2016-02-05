package org.uva.sea.ql.ast.val;

import org.uva.sea.ql.ast.Node;
import org.uva.sea.ql.ast.checker.Visitable;
import org.uva.sea.ql.ast.checker.Visitor;

import java.util.List;

/**
 * Created by roydewildt on 04/02/16.
 */
abstract public class Val implements Node, Visitable {
    private String value;

    public Val(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "(" + value + ")";
    }

    public List<String> accept(Visitor visitor) {
        return visitor.visit(this);
    }

    public String getValue() {
        return value;
    }
}
