package org.uva.sea.ql.ast.expr;

import java.util.Objects;

public class Concat extends Expr {
    
    public static final String NON_STRING_OPERANDS_MESSAGE = "Can not perform concatenation on non-string operands";
    
    private Expr firstValue;
    private Expr secondValue;
    
    public Concat(Expr theFirstValue, Expr theSecondValue) {
        super(false, false, true);
        if (theFirstValue.canBeString() && theSecondValue.canBeString()) {
            firstValue = theFirstValue;
            secondValue = theSecondValue;
        }
        else {
            throwNonStringOperandsException();
        }
    }
    
    protected void throwNonStringOperandsException() {
        throw new IllegalArgumentException(NON_STRING_OPERANDS_MESSAGE);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.firstValue);
        hash = 23 * hash + Objects.hashCode(this.secondValue);
        return hash;
    }
    
    @Override
    public boolean equals(Object o) {
        if (getClass() == o.getClass()) {
            Concat other = (Concat) o;
            return firstValue.equals(other.firstValue)
                    && secondValue.equals(other.secondValue);
        }
        return false;
    }
    
}
