package org.uva.sea.ql.ast;

import org.uva.sea.ql.ast.expr.*;

public class Question extends ASTNode {
    
    public static final int HASH_ORIGIN = 7;
    public static final int HASH_FACTOR = 23;
    
    private final Ident identifier;
    private final Label label;
    private final ASTNode type;
    
    public Question(Ident theIdentifier, Label theLabel, ASTNode theType) {
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
        int hash = HASH_ORIGIN;
        hash = HASH_FACTOR * hash + identifier.hashCode();
        hash = HASH_FACTOR * hash + label.hashCode();
        hash = HASH_FACTOR * hash + type.hashCode();
        return hash;
    }
    
}
