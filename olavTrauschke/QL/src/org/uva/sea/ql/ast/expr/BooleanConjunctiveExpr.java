package org.uva.sea.ql.ast.expr;

public class BooleanConjunctiveExpr extends BooleanExpr {
    
    private final Expr firstExpr;
    private final Expr secondExpr;
    
    public BooleanConjunctiveExpr(Expr theFirstExpr, Expr theSecondExpr) {
        assert theFirstExpr != null && theSecondExpr != null;
        firstExpr = theFirstExpr;
        secondExpr = theSecondExpr;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o != null && getClass() == o.getClass()) {
            BooleanConjunctiveExpr other = (BooleanConjunctiveExpr) o;
            return firstExpr.equals(other.firstExpr) && secondExpr.equals(other.secondExpr);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + firstExpr.hashCode();
        hash = 29 * hash + secondExpr.hashCode();
        return hash;
    }
    
}
