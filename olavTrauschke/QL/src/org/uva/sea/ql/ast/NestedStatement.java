package org.uva.sea.ql.ast;

import java.util.Objects;

public class NestedStatement extends ASTNode {
    
    private final ASTNode statement;
    private final ASTNode nestedStatement;
    
    public NestedStatement(ASTNode theStatement, ASTNode theNestedStatement) {
        assert theStatement != null;
        statement = theStatement;
        nestedStatement = theNestedStatement;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o != null && getClass() == o.getClass()) {
            NestedStatement other = (NestedStatement) o;
            return statement.equals(other.statement)
                    && nestedStatement.equals(other.nestedStatement);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + statement.hashCode();
        hash = 29 * hash + Objects.hashCode(this.nestedStatement);
        return hash;
    }
    
}
