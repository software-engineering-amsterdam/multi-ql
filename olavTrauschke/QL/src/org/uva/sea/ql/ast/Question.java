package org.uva.sea.ql.ast;

import java.util.Objects;
import org.uva.sea.ql.ast.expr.*;

public class Question extends ASTNode {
    
    private Ident identifier;
    private Str label;
    private Int type;
    
    public Question(Ident theIdentifier, Str theLabel, Int theType) {
        identifier = theIdentifier;
        label = theLabel;
        type = theType;
    }
    
    @Override
    public boolean equals(Object o) {
        if (getClass() == o.getClass()) {
            Question other = (Question) o;
            return identifier.equals(other.identifier);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.identifier);
        return hash;
    }
    
    //Stricter implementation of equals for testing purpose
    public boolean completelyEquals(Question other) {
        return identifier.equals(other.identifier) && label.equals(other.label) && type.equals(other.type);
    }
    
}
