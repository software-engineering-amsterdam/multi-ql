package org.uva.sea.ql.ast.expr;

import java.util.Objects;

public class Money extends Expr {
    
    public static final int HASH_ORIGIN = 3;
    public static final int HASH_FACTOR = 83;
    
    private Long units;
    private Byte cents;
    
    public Money(Long theUnits, Byte theCents) {
        units = theUnits;
        cents = theCents;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        
        Money other = (Money) o;
        boolean unitsEqual = units == null ? other.units == null : units.equals(other.units);
        boolean centsEqual = cents == null ? other.cents == null : cents.equals(other.cents);
        return unitsEqual && centsEqual;
    }

    @Override
    public int hashCode() {
        int hash = HASH_ORIGIN;
        hash = HASH_FACTOR * hash + Objects.hashCode(this.units);
        hash = HASH_FACTOR * hash + Objects.hashCode(this.cents);
        return hash;
    }
    
}
