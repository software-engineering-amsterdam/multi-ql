package org.uva.sea.ql.ast;

import org.uva.sea.ql.ast.expr.Ident;

public class Form extends ASTNode {
    
    private final Ident identifier;
    private final ASTNode questions;
    
    public Form(Ident theIdentifier, ASTNode theQuestions) {
        assert theIdentifier != null && theQuestions != null;
        identifier = theIdentifier;
        questions = theQuestions;
    }
    
    @Override
    public boolean equals(Object o) {
        return o != null
                && getClass() == o.getClass()
                && identifier.equals(((Form) o).identifier);
    }
    
    @Override
    public int hashCode() {
        return 29 * 3 + identifier.hashCode();
    }
    
}
