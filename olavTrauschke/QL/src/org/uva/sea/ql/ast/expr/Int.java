package org.uva.sea.ql.ast.expr;

public class Int extends NumericExpr {
    
    private int value;
    
    public Int(int theValue) {
        value = theValue;
    }
    
    @Override
    public boolean equals(Object o) {
        return super.equals(o) && value == ((Int) o).value;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 23 * hash + this.value;
        return hash;
    }
}
