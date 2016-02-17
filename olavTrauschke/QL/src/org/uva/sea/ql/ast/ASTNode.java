package org.uva.sea.ql.ast;

import java.util.ArrayList;
import java.util.List;

public abstract class ASTNode {
    
    private List<String> errors;
    
    protected ASTNode() {
        errors = new ArrayList<>();
    }
    
    @Override
    public boolean equals(Object o) {
        return o != null && getClass() == o.getClass();
    }

    @Override
    public int hashCode() {
        return 0;
    }
    
    protected final void addError(String message) {
        errors.add(message);
    }
}
