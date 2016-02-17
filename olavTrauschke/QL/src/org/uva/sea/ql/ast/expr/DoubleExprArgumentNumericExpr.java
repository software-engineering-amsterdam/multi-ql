package org.uva.sea.ql.ast.expr;

import java.util.Objects;

public abstract class DoubleExprArgumentNumericExpr extends NumericExpr {
    
    private Expr firstExpr;
    private Expr secondExpr;
    
    public DoubleExprArgumentNumericExpr(Expr theFirstExpr, Expr theSecondExpr) {
        if (theFirstExpr.canBeNumeric() && theSecondExpr.canBeNumeric()) {
            firstExpr = theFirstExpr;
            secondExpr = theSecondExpr;
        }
        else {
            addError(TYPE_ERROR_MESSAGE);
        }
    }
    
    @Override
    public boolean equals(Object o) {
        if (super.equals(o)) {
            DoubleExprArgumentNumericExpr other = (DoubleExprArgumentNumericExpr) o;
            return firstExpr.equals(other.firstExpr) && secondExpr.equals(other.secondExpr);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 89 * hash + Objects.hashCode(this.firstExpr);
        hash = 89 * hash + Objects.hashCode(this.secondExpr);
        return hash;
    }
    
}
