package org.uva.sea.ql.ast.expr;

import java.util.Objects;

public class BooleanConjunctiveExpr extends BooleanExpr {
    
    public static final String TYPE_ERROR_MESSAGE = "Can not perform boolean conjunction operation on non-boolean operands";
    
    private Expr firstExpr;
    private Expr secondExpr;
    
    public BooleanConjunctiveExpr(Expr theFirstExpr, Expr theSecondExpr) {
        if (theFirstExpr.canBeBoolean() && theSecondExpr.canBeBoolean()) {
            firstExpr = theFirstExpr;
            secondExpr = theSecondExpr;
        }
        else {
            setError(TYPE_ERROR_MESSAGE);
        }
    }
    
    @Override
    public boolean equals(Object o) {
        if (super.equals(o)) {
            BooleanConjunctiveExpr other = (BooleanConjunctiveExpr) o;
            return firstExpr.equals(other.firstExpr) && secondExpr.equals(other.secondExpr);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 29 * hash + Objects.hashCode(this.firstExpr);
        hash = 29 * hash + Objects.hashCode(this.secondExpr);
        return hash;
    }
    
}
