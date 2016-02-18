package org.uva.sea.ql.ast.expr;

import java.util.Objects;

public class Int extends NumericExpr {
    
    private Integer value;
    
    public Int(Integer theValue) {
        value = theValue;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o != null && getClass() == o.getClass()) {
            Int other = (Int) o;
            return value == null ? other.value == null : value.equals(other.value);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.value);
        return hash;
    }
}
