package org.uva.sea.ql.ast.tree.val;

import org.uva.sea.ql.ast.visitor.Visitor;

/**
 * Created by roydewildt on 04/02/16.
 */
public class Int extends Val {
    Integer value;

    public Int(int line, String x){
        super(line);
        Integer.valueOf(x);
    }

    public <T> T accept(Visitor visitor) {
        return visitor.visit(this);
    }

    public Integer getValue() {
        return value;
    }
}
