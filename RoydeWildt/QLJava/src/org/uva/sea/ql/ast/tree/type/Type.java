package org.uva.sea.ql.ast.tree.type;

import org.uva.sea.ql.ast.tree.Node;

/**
 * Created by roydewildt on 11/02/16.
 */
public abstract class Type extends Node {

    public enum Types {BOOLEAN, MONEY}
    public abstract Types getType();

    public Type(int line) {
        super(line);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName().toString();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Type){
            Type toCompare = (Type) obj;
            return this.getClass().getSimpleName().equals(toCompare.getClass().getSimpleName());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.getClass().getSimpleName().hashCode();
    }
}
