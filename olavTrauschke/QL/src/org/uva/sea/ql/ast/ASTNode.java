package org.uva.sea.ql.ast;

public abstract class ASTNode {
    
    @Override
    public abstract boolean equals(Object o);
    
    @Override
    public abstract int hashCode();
}
