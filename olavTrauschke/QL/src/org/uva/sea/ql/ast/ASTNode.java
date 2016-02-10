package org.uva.sea.ql.ast;

public abstract class ASTNode {
    
    //Force subclasses to implement equals
    @Override
    public abstract boolean equals(Object o);
    
    //Force subclasses to implement hashCode
    @Override
    public abstract int hashCode();
}
