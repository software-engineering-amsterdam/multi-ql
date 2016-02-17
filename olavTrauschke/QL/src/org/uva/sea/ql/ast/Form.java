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
        if (getClass() == o.getClass()) {
            Form other = (Form) o;
            return identifier.equals(other.identifier);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.identifier);
        return hash;
    }
    
}
