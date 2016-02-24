package org.uva.sea.ql.ast.expr;

import java.util.Objects;

public class Str extends Expr {
    
    public static final int HASH_ORIGIN = 249;
    
    private final String value;
    
    public Str(String theValue) {
        value = theValue;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        
        Str other = (Str) o;
        return value == null ? other.value == null : value.equals(other.value);
    }

    @Override
    public int hashCode() {
        return HASH_ORIGIN + Objects.hashCode(this.value);
    }
    
    public String getValue() {
        return value;
    }
    
}
