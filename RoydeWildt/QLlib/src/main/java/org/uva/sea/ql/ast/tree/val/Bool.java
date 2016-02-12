package org.uva.sea.ql.ast.tree.val;

import org.uva.sea.ql.ast.visitor.Visitor;

/**
 * Created by roydewildt on 04/02/16.
 */
public class Bool extends Val {
    private Boolean value;

    public Bool(int line, String x){
        super(line);
        this.value = Boolean.valueOf(x);
    }

    public <T> T accept(Visitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return value.toString();
    }

    public Boolean getValue() {
        return value;
    }
}
