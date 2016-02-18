package org.uva.sea.ql.ast;

import java.util.Objects;
import org.uva.sea.ql.ast.expr.Expr;

public class ConditionalStatement extends ASTNode {
    
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
        if (o != null && getClass() == o.getClass()) {
            ConditionalStatement other = (ConditionalStatement) o;
            if (condition.equals(other.condition) && toDoIf.equals(other.toDoIf)) {
                return toDoElse == null ? other.toDoElse == null : toDoElse.equals(other.toDoElse);
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + condition.hashCode();
        hash = 79 * hash + toDoIf.hashCode();
        hash = 79 * hash + Objects.hashCode(this.toDoElse);
        return hash;
    }
}
