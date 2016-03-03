package org.uva.sea.ql.ast.tree.type;

import org.uva.sea.ql.ast.tree.Node;
import org.uva.sea.ql.ast.tree.val.Val;
import org.uva.sea.ql.ast.type.ValueType;
import org.uva.sea.ql.ast.visitor.interfaces.ITypeVisitable;

/**
 * Created by roydewildt on 11/02/16.
 */
public abstract class Type extends Node implements ITypeVisitable {

    public Type(int line) {
        super(line);
    }

    public abstract ValueType getType();

    public abstract Val getDefaultValue();

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
