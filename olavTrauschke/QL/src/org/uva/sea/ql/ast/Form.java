package org.uva.sea.ql.ast;

import java.util.Objects;
import org.uva.sea.ql.ast.expr.Ident;

public class Form extends ASTNode {
    
    private Ident identifier;
    private ASTNode questions;
    
    public Form(Ident theIdentifier, ASTNode theQuestions) {
        identifier = theIdentifier;
        questions = theQuestions;
    }
    
    @Override
    public boolean equals(Object o) {
        return super.equals(o) && identifier.equals(((Form) o).identifier);
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 29 * hash + Objects.hashCode(this.identifier);
        return hash;
    }
    
}
