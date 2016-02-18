package org.uva.sea.ql.ast.expr;

import java.util.Objects;

public class Money extends Expr {
    
    private Long units;
    private Byte cents;
    
    public Money(Long theUnits, Byte theCents) {
        units = theUnits;
        cents = theCents;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o != null && getClass() == o.getClass()) {
            Money other = (Money) o;
            boolean unitsEqual = units == null ? other.units == null : units.equals(other.units);
            boolean centsEqual = cents == null ? other.cents == null : cents.equals(other.cents);
            return unitsEqual && centsEqual;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.units);
        hash = 83 * hash + Objects.hashCode(this.cents);
        return hash;
    }
    
}
