package org.uva.sea.ql.ast.expr;

import java.util.Objects;


public abstract class ComparisonExpr extends BooleanExpr {
    
    private Expr firstExpr;
    private Expr secondExpr;
    
    public ComparisonExpr(Expr theFirstExpr, Expr theSecondExpr) {
        if ((theFirstExpr.canBeBoolean() && theSecondExpr.canBeBoolean())
                || (theFirstExpr.canBeNumeric() && theSecondExpr.canBeNumeric())) {
            firstExpr = theFirstExpr;
            secondExpr = theSecondExpr;
        }
        else {
            throw new IllegalArgumentException("The arguments of a comparison should be of the same type");
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.firstExpr);
        hash = 67 * hash + Objects.hashCode(this.secondExpr);
        return hash;
    }
    
    @Override
    public boolean equals(Object o) {
        if (getClass().equals(o.getClass())) {
            ComparisonExpr other = (ComparisonExpr) o;
            return firstExpr.equals(other.firstExpr) && secondExpr.equals(other.secondExpr);
        }
        return false;
    }
}
