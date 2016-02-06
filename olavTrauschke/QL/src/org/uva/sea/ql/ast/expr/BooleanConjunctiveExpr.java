package org.uva.sea.ql.ast.expr;

import java.util.Objects;

public class BooleanConjunctiveExpr extends BooleanExpr {
    private BooleanExpr firstExpr;
    private BooleanExpr secondExpr;
    
    public BooleanConjunctiveExpr(Expr theFirstExpr, Expr theSecondExpr) {
        firstExpr = (BooleanExpr) theFirstExpr;
        secondExpr = (BooleanExpr) theSecondExpr;
    }
    
    @Override
    public boolean equals(Object o) {
        if (getClass().equals(o.getClass())) {
            BooleanConjunctiveExpr other = (BooleanConjunctiveExpr) o;
            return firstExpr.equals(other.firstExpr) && secondExpr.equals(other.secondExpr);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.firstExpr);
        hash = 29 * hash + Objects.hashCode(this.secondExpr);
        return hash;
    }
    
}
