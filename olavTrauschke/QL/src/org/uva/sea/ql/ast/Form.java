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
        if (o != null && getClass() == o.getClass()) {
            Form other = (Form) o;
            return identifier.equals(other.identifier) && questions.equals(other.questions);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return 29 * 3 + identifier.hashCode();
    }
    
}
