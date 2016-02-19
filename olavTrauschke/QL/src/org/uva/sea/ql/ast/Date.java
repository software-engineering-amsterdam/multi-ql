package org.uva.sea.ql.ast;

public class Date extends ASTNode {
    
    public static final int HASH_ORIGIN = 3;
    
    @Override
    public boolean equals(Object o) {
        return o != null && getClass() == o.getClass();
    }

    @Override
    public int hashCode() {
        return HASH_ORIGIN;
    }
    
}
