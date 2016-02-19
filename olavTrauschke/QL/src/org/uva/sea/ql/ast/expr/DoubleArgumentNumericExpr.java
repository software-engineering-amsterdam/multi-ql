package org.uva.sea.ql.ast.expr;

public abstract class DoubleArgumentNumericExpr extends NumericExpr {
    
    public static final int HASH_ORIGIN = 5;
    public static final int HASH_FACTOR = 97;
    
    private final Expr firstExpr;
    private final Expr secondExpr;
    
    public DoubleArgumentNumericExpr(Expr theFirstExpr, Expr theSecondExpr) {
        assert theFirstExpr != null && theSecondExpr != null;
        firstExpr = theFirstExpr;
        secondExpr = theSecondExpr;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o != null && getClass() == o.getClass()) {
            DoubleArgumentNumericExpr other = (DoubleArgumentNumericExpr) o;
            return firstExpr.equals(other.firstExpr) && secondExpr.equals(other.secondExpr);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = HASH_ORIGIN;
        hash = HASH_FACTOR * hash + firstExpr.hashCode();
        hash = HASH_FACTOR * hash + secondExpr.hashCode();
        return hash;
    }
    
}
