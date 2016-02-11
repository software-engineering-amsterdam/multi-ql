package org.uva.sea.ql.ast.expr;

import java.util.Objects;

public class Str extends Expr {
    
    private String value;
    
    public Str(String theValue) {
        super(false, false, true);
        value = theValue;
    }
    
    @Override
    public boolean equals(Object o) {
        if (getClass() == o.getClass()) {
            Str other = (Str) o;
            return value.equals(other.value);
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
