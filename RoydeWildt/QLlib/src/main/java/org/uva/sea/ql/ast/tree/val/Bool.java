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

    public <T,U> T accept(Visitor<T,U> visitor, U context) {
        return visitor.visit(this, context);
    }

    @Override
    public String toString() {
        return value.toString();
    }

    public Boolean getValue() {
        return value;
    }
}
