package org.uva.sea.ql.ast.expr;

public class Int extends NumericExpr {
    
    private final int value;
    
    public Int(int theValue) {
        value = theValue;
    }
    
    @Override
    public boolean equals(Object o) {
        return o != null
                && getClass() == o.getClass()
                && value == ((Int) o).value;
    }

    @Override
    public int hashCode() {
        return 43 * 7 + value;
    }
}
