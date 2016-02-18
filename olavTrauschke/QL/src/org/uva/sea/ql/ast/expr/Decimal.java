package org.uva.sea.ql.ast.expr;

import java.util.Objects;

public class Decimal extends NumericExpr {
    
    private Double value;
    
    public Decimal(Double theValue) {
        value = theValue;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o != null && getClass() == o.getClass()) {
            Decimal other = (Decimal) o;
            return value == null ? other.value == null : value.equals(other.value);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.value);
        return hash;
    }
}
