package org.uva.sea.ql.ast.type;

/**
 * Created by roydewildt on 24/02/16.
 */
public abstract class ValueType {
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ValueType){
            ValueType toCompare = (ValueType) obj;
            return this.getClass().getSimpleName().equals(toCompare.getClass().getSimpleName());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return getClass().getSimpleName().hashCode();
    }
}
