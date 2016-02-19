package org.uva.sea.ql.ast.expr;

public abstract class DoubleArgumentNumericExpr extends NumericExpr {
    
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
        int hash = 5;
        hash = 97 * hash + firstExpr.hashCode();
        hash = 97 * hash + secondExpr.hashCode();
        return hash;
    }
    
}
