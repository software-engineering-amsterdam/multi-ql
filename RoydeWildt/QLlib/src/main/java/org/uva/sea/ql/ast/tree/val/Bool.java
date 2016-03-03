package org.uva.sea.ql.ast.tree.val;

import org.uva.sea.ql.ast.visitor.interfaces.IValVisitor;

/**
 * Created by roydewildt on 04/02/16.
 */
public class Bool extends Val {
    private final Boolean value;

    public Bool(boolean b){
        super(0);
        this.value = b;
    }

    public Bool(int line, String x){
        super(line);
        this.value = Boolean.valueOf(x);
    }

    public Boolean getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public <S, C> S accept(IValVisitor<S, C> visitor, C context) {
        return visitor.visit(this, context);
    }
}
