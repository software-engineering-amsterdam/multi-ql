package org.uva.sea.ql.ast.expr;

import java.util.Objects;

public class Str extends Expr {
    
    private final String value;
    
    public Str(String theValue) {
        value = theValue;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o != null && getClass() == o.getClass()) {
            Str other = (Str) o;
            return value == null ? other.value == null : value.equals(other.value);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.value);
        return hash;
    }
    
    public String getValue() {
        return value;
    }
    
}
