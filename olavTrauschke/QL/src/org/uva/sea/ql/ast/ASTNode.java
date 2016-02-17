package org.uva.sea.ql.ast;

import java.util.ArrayList;
import java.util.List;

public abstract class ASTNode {
    
    private List<String> errors;
    
    protected ASTNode() {
        errors = new ArrayList<>();
    }
    
    protected final void addError(String message) {
        errors.add(message);
    }
}
