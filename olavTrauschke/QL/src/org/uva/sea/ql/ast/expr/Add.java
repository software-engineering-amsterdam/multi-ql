package org.uva.sea.ql.ast.expr;

import java.util.Objects;

public class Add extends Expr {
    
    private Expr firstExpr;
    private Expr secondExpr;
    
    public Add(Expr theFirstExpr, Expr theSecondExpr) {
        super(false, true, true);
        boolean canBeNumeric = theFirstExpr.canBeNumeric() && theSecondExpr.canBeNumeric();
        boolean canBeString = theFirstExpr.canBeString() && theSecondExpr.canBeString();
        if (canBeNumeric || canBeString) {
            firstExpr = theFirstExpr;
            secondExpr = theSecondExpr;
            setCanBeNumeric(canBeNumeric);
            setCanBeString(canBeString);
        }
        else {
            throw new IllegalArgumentException("The arguments of the + operator should either both be numeric or both be strings");
        }
    }
    
    @Override
    public boolean equals(Object o) {
        if (getClass() == o.getClass()) {
            Add other = (Add) o;
            return firstExpr.equals(other.firstExpr) && secondExpr.equals(other.secondExpr);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.firstExpr);
        hash = 73 * hash + Objects.hashCode(this.secondExpr);
        return hash;
    }
}
