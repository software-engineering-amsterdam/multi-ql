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
        return super.equals(o) && value.equals(((Str) o).value);
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 37 * hash + Objects.hashCode(this.value);
        return hash;
    }
    
}
