package org.uva.sea.ql.ast;

import java.util.Objects;
import org.uva.sea.ql.ast.expr.Expr;

public class ConditionalStatement extends ASTNode {
    
    public static final int HASH_ORIGIN = 3;
    public static final int HASH_FACTOR = 79;
    
    private final Expr condition;
    private final ASTNode toDoIf;
    private final ASTNode toDoElse;
    
    public ConditionalStatement(Expr theCondition, ASTNode toDoInCase, ASTNode toDoInCaseNot) {
        assert theCondition != null && toDoInCase != null;
        condition = theCondition;
        toDoIf = toDoInCase;
        toDoElse = toDoInCaseNot;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        
        ConditionalStatement other = (ConditionalStatement) o;
        if (condition.equals(other.condition) && toDoIf.equals(other.toDoIf)) {
            return toDoElse == null ? other.toDoElse == null : toDoElse.equals(other.toDoElse);
        }
        else return false;
    }

    @Override
    public int hashCode() {
        int hash = HASH_ORIGIN;
        hash = HASH_FACTOR * hash + condition.hashCode();
        hash = HASH_FACTOR * hash + toDoIf.hashCode();
        hash = HASH_FACTOR * hash + Objects.hashCode(this.toDoElse);
        return hash;
    }
}
