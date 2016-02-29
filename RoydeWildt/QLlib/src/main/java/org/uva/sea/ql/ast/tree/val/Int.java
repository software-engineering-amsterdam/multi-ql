package org.uva.sea.ql.ast.tree.val;

import org.uva.sea.ql.ast.visitor.interfaces.IValVisitor;

/**
 * Created by roydewildt on 04/02/16.
 */
public class Int extends Val {
    private Integer value;

    public Int(int i){
        super(0);
        this.value = i;}

    public Int(int line, String x){
        super(line);
        this.value = Integer.valueOf(x);
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
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
