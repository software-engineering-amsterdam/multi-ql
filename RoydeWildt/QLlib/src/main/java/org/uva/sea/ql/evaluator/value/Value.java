package org.uva.sea.ql.evaluator.value;

/**
 * Created by roydewildt on 16/03/16.
 */
public abstract class Value {

    public abstract Object getValue();


    public Bool Eq(Object x){
        return new Bool(this.equals(x));
    }
    public Bool NEq(Object x){
        return new Bool(!this.equals(x));
    }
}
