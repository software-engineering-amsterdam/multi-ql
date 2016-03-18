package org.uva.sea.ql.adt.value;

import org.uva.sea.ql.adt.type.Boolean;
import org.uva.sea.ql.adt.type.Type;
import org.uva.sea.ql.adt.visitor.ValueVisitor;

/**
 * Created by roydewildt on 16/03/16.
 */
public class Bool extends Value {
    private java.lang.Boolean value;

    public Bool(java.lang.Boolean value) {
        this.value = value;
    }

    @Override
    public java.lang.Boolean getValue() {
        return this.value;
    }

    @Override
    public Type getType() {
        return new Boolean();
    }

    public Bool And(Bool x){
        return new Bool(this.value && x.getValue());
    }

    public Bool Or(Bool x){
        return new Bool(this.value || x.getValue());
    }

    public Bool Not(){
        return new Bool(!this.value);
    }

    @Override
    public Bool Eq(Object x) {
        if(x instanceof Bool){
            boolean ybool = this.getValue();
            boolean xbool = ((Bool) x).getValue();
            return new Bool(ybool == xbool);
        }
        return new Bool(false);
    }

    @Override
    public <ATOM, CONTEXT> ATOM accept(ValueVisitor<ATOM, CONTEXT> visitor, CONTEXT context) {
        return visitor.visit(this, context);
    }
}
