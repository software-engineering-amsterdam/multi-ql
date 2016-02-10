package org.uva.sea.ql.ast.expr;

import java.util.Objects;

public class Not extends BooleanExpr {
    
    public static final String NON_BOOLEAN_OPERAND_MESSAGE = "Can not negate non-boolean operand";
    
    private Expr content;
    
    public Not(Expr theContent) {
        if (theContent.canBeBoolean()) {
            content = theContent;
        }
        else {
            throwNonBooleanOperandException();
        }
    }
    
    protected void throwNonBooleanOperandException() {
        throw new IllegalArgumentException(NON_BOOLEAN_OPERAND_MESSAGE);
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof Not) {
            Not other = (Not) o;
            return content.equals(other.content);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.content);
        return hash;
    }
    
}
