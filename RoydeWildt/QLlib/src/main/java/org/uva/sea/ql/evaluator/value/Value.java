package org.uva.sea.ql.evaluator.value;

import org.uva.sea.ql.evaluator.value.visitor.ValueVisitable;
import org.uva.sea.ql.evaluator.value.visitor.ValueVisitor;

/**
 * Created by roydewildt on 16/03/16.
 */
public abstract class Value implements ValueVisitable {

    public abstract Object getValue();


    public Bool Eq(Object x){
        return new Bool(this.equals(x));
    }
    public Bool NEq(Object x){
        return new Bool(!this.equals(x));
    }

    public abstract <ATOM, CONTEXT> ATOM accept(ValueVisitor<ATOM, CONTEXT> visitor, CONTEXT context);
}
