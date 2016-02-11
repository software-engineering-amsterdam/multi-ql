package org.uva.sea.ql.ast.val;

import org.uva.sea.ql.ast.visitor.Visitor;

/**
 * Created by roydewildt on 04/02/16.
 */
public class Bool extends Val {
    Boolean value;

    public Bool () {super(0);}
    public Bool(int line, String x){
        super(line);
        this.value = Boolean.valueOf(x);
    }

    public <T> T accept(Visitor visitor) {
        return visitor.visit(this);
    }

}
