package org.uva.sea.ql.ast.expr;

import java.util.Objects;

public class Bool extends BooleanExpr {
    
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
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.value);
        return hash;
    }
    
}
