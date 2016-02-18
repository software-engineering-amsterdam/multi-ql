package org.uva.sea.ql.ast.expr;

public class Str extends Expr {
    
    private final String value;
    
    public Str(String theValue) {
        assert theValue != null;
        value = theValue;
    }
    
    @Override
    public boolean equals(Object o) {
        return o != null
                && getClass() == o.getClass()
                && value.equals(((Str) o).value);
    }

    @Override
    public int hashCode() {
        return 53 * 3 + value.hashCode();
    }
    
    public String getValue() {
        return value;
    }
    
}
