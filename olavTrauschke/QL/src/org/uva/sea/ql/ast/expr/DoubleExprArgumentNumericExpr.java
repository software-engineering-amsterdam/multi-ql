package org.uva.sea.ql.ast.expr;

public abstract class DoubleExprArgumentNumericExpr extends NumericExpr {
    
    private Expr firstExpr;
    private Expr secondExpr;
    
    public DoubleExprArgumentNumericExpr(Expr theFirstExpr, Expr theSecondExpr) {
        assert theFirstExpr != null && theSecondExpr != null;
        firstExpr = theFirstExpr;
        secondExpr = theSecondExpr;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o != null && getClass() == o.getClass()) {
            DoubleExprArgumentNumericExpr other = (DoubleExprArgumentNumericExpr) o;
            return firstExpr.equals(other.firstExpr) && secondExpr.equals(other.secondExpr);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 89 * hash + firstExpr.hashCode();
        hash = 89 * hash + secondExpr.hashCode();
        return hash;
    }
    
}
