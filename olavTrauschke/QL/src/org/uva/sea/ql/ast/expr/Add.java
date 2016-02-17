package org.uva.sea.ql.ast.expr;

public class Add extends Expr {
    
    private final Expr firstExpr;
    private final Expr secondExpr;
    
    public Add(Expr theFirstExpr, Expr theSecondExpr) {
        assert theFirstExpr != null & theSecondExpr != null;
        firstExpr = theFirstExpr;
        secondExpr = theSecondExpr;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o != null && getClass() == o.getClass()) {
            Add other = (Add) o;
            return firstExpr.equals(other.firstExpr) && secondExpr.equals(other.secondExpr);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + firstExpr.hashCode();
        hash = 67 * hash + secondExpr.hashCode();
        return hash;
    }
}
