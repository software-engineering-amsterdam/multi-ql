package org.uva.sea.ql.ast.expr;

public class Int extends NumericExpr {
    
    private int value;
    
    public Int(int theValue) {
        value = theValue;
    }
    
    @Override
    public boolean equals(Object o) {
        if (getClass() == o.getClass()) {
            Int other = (Int) o;
            return value == other.value;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + this.value;
        return hash;
    }
}
