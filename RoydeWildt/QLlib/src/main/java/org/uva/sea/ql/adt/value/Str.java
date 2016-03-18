package org.uva.sea.ql.adt.value;

import org.uva.sea.ql.adt.type.String;
import org.uva.sea.ql.adt.type.Type;
import org.uva.sea.ql.adt.visitor.ValueVisitor;

/**
 * Created by roydewildt on 16/03/16.
 */
public class Str extends Value {
    private java.lang.String value;

    public Str(java.lang.String value) {
        this.value = value;
    }

    public java.lang.String getValue() {
        return this.value;
    }

    @Override
    public Type getType() {
        return  new String();
    }

    @Override
    public Bool Eq(Object x) {
        if(x instanceof Str){
            java.lang.String ystr = this.getValue();
            java.lang.String xstr = ((Str) x).getValue();
            return new Bool(ystr.equals(xstr));
        }
        return new Bool(false);
    }

    @Override
    public <ATOM, CONTEXT> ATOM accept(ValueVisitor<ATOM, CONTEXT> visitor, CONTEXT context) {
        return visitor.visit(this, context);
    }
}
