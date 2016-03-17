package org.uva.sea.ql.evaluator.value;

import org.uva.sea.ql.evaluator.value.visitor.ValueVisitable;
import org.uva.sea.ql.evaluator.value.visitor.ValueVisitor;

/**
 * Created by roydewildt on 16/03/16.
 */
public abstract class Value implements ValueVisitable {

    public abstract Object getValue();


    public abstract Bool Eq(Object x);
    public Bool NEq(Object x){
        Bool b = this.Eq(x);
        return new Bool(!b.getValue());
    }

    public abstract <ATOM, CONTEXT> ATOM accept(ValueVisitor<ATOM, CONTEXT> visitor, CONTEXT context);
}
