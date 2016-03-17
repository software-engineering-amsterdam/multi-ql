package org.uva.sea.ql.ast.tree.type;

import org.antlr.v4.runtime.Token;
import org.uva.sea.ql.ast.tree.Node;
import org.uva.sea.ql.ast.tree.atom.val.Val;
import org.uva.sea.ql.ast.visitor.interfaces.TypeVisitable;

/**
 * Created by roydewildt on 11/02/16.
 */
public abstract class Type extends Node implements TypeVisitable {

    public Type(Token token) {
        super(token);
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
