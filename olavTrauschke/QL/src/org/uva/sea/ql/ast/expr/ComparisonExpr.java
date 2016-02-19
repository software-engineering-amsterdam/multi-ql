package org.uva.sea.ql.ast.expr;

public abstract class ComparisonExpr extends BooleanExpr {
    
    private final Expr firstExpr;
    private final Expr secondExpr;
    
    public ComparisonExpr(Expr theFirstExpr, Expr theSecondExpr) {
        assert theFirstExpr != null && theSecondExpr != null;
        firstExpr = theFirstExpr;
        secondExpr = theSecondExpr;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o != null && getClass() == o.getClass()) {
            ComparisonExpr other = (ComparisonExpr) o;
            return firstExpr.equals(other.firstExpr) && secondExpr.equals(other.secondExpr);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + firstExpr.hashCode();
        hash = 83 * hash + secondExpr.hashCode();
        return hash;
    }
}
