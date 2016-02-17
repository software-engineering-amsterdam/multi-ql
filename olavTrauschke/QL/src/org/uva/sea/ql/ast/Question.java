package org.uva.sea.ql.ast;

import org.uva.sea.ql.ast.expr.*;

public class Question extends ASTNode {
    
    private final Ident identifier;
    private final Str label;
    private final Int type;
    
    public Question(Ident theIdentifier, Str theLabel, Int theType) {
        assert theIdentifier != null && theLabel != null && theType != null;
        identifier = theIdentifier;
        label = theLabel;
        type = theType;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o != null && getClass() == o.getClass()) {
            Question other = (Question) o;
            return identifier.equals(other.identifier)
                    && label.equals(other.label)
                    && type.equals(other.type);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + identifier.hashCode();
        hash = 59 * hash + label.hashCode();
        hash = 59 * hash + type.hashCode();
        return hash;
    }
    
}
