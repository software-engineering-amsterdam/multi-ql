package org.uva.sea.ql.ast.expr;

import java.util.Objects;


public abstract class ComparisonExpr extends BooleanExpr {
    
    public static final String TYPE_ERROR_MESSAGE = "The arguments of a comparison should either both be booleans or both be numeric";
    
    private Expr firstExpr;
    private Expr secondExpr;
    
    public ComparisonExpr(Expr theFirstExpr, Expr theSecondExpr) {
        boolean canBeBoolean = theFirstExpr.canBeBoolean() && theSecondExpr.canBeBoolean();
        boolean canBeNumeric = theFirstExpr.canBeNumeric() && theSecondExpr.canBeNumeric();
        if (canBeBoolean || canBeNumeric) {
            firstExpr = theFirstExpr;
            secondExpr = theSecondExpr;
        }
        else {
            setError(TYPE_ERROR_MESSAGE);
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
        if (getClass() == o.getClass()) {
            ComparisonExpr other = (ComparisonExpr) o;
            return firstExpr.equals(other.firstExpr) && secondExpr.equals(other.secondExpr);
        }
        return false;
    }
}
