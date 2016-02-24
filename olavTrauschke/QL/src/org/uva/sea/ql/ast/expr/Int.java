package org.uva.sea.ql.ast.expr;

import java.util.Objects;

public class Int extends NumericExpr {
    
    public static final int HASH_ORIGIN = 259;
    
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
        return HASH_ORIGIN + Objects.hashCode(this.value);
    }
}
