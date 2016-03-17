package org.uva.sea.ql.evaluator.value;

import org.uva.sea.ql.evaluator.value.visitor.ValueVisitor;

/**
 * Created by roydewildt on 16/03/16.
 */
public class Bool extends Value {
    private Boolean value;

    public Bool(Boolean value) {
        this.value = value;
    }

    @Override
    public Boolean getValue() {
        return null;
    }

    public Bool And(Bool x){
        return new Bool(this.value && x.getValue());
    }

    public Bool Or(Bool x){
        return new Bool(this.value || x.getValue());
    }

    @Override
    public <ATOM, CONTEXT> ATOM accept(ValueVisitor<ATOM, CONTEXT> visitor, CONTEXT context) {
        return visitor.visit(this, context);
    }
}
