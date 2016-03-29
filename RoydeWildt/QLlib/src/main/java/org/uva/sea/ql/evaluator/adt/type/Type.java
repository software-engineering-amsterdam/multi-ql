package org.uva.sea.ql.evaluator.adt.type;

/**
 * Created by roy on 3/18/16.
 */
public abstract class Type {

    @Override
    public boolean equals(Object o) {
        if(o instanceof Type){
            Type toCompare = (Type) o;
            return this.getClass().getName().equals(toCompare.getClass().getName());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return getClass().getName().hashCode();
    }
}
