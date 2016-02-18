package org.uva.sea.ql.ast;

public class Date extends ASTNode {
    
    @Override
    public boolean equals(Object o) {
        return o != null && getClass() == o.getClass();
    }

    @Override
    public int hashCode() {
        return 3;
    }
    
}
