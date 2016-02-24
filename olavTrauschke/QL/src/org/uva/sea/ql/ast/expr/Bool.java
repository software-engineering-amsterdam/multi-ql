package org.uva.sea.ql.ast.expr;

import java.util.Objects;

public class Bool extends BooleanExpr {
    
    public static final int HASH_ORIGIN = 623;
    
    private Boolean value;
    
    public Bool(Boolean theValue) {
        value = theValue;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o != null && getClass() == o.getClass()) {
            Bool other = (Bool) o;
            if (value == null) {
                return other.value == null;
            }
            else {
                return value.equals(other.value);
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return HASH_ORIGIN + Objects.hashCode(this.value);
    }
    
}
