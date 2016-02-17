package org.uva.sea.ql.ast.expr;

public class Bool extends BooleanExpr {
    
    private final boolean value;
    
    public Bool(boolean theValue) {
        value = theValue;
    }
    
    @Override
    public boolean equals(Object o) {
        return o != null
                && getClass() == o.getClass()
                && value == ((Bool) o).value;
    }

    @Override
    public int hashCode() {
        return 71 * 7 + (value ? 1 : 0);
    }
    
}
