package org.uva.sea.ql.evaluator.value;

import org.uva.sea.ql.evaluator.value.visitor.ValueVisitor;

/**
 * Created by roydewildt on 16/03/16.
 */
public class String extends Value {
    private java.lang.String value;

    public String(java.lang.String value) {
        this.value = value;
    }

    public java.lang.String getValue() {
        return this.value;
    }

    @Override
    public Bool Eq(Object x) {
        if(x instanceof String){
            java.lang.String ystr = this.getValue();
            java.lang.String xstr = ((String) x).getValue();
            return new Bool(ystr.equals(xstr));
        }
        return new Bool(false);
    }

    @Override
    public <ATOM, CONTEXT> ATOM accept(ValueVisitor<ATOM, CONTEXT> visitor, CONTEXT context) {
        return visitor.visit(this, context);
    }
}
