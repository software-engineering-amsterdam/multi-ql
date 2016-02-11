package org.uva.sea.ql.ast.expr;

public class Bool extends BooleanExpr {
    
    private boolean value;
    
    public Bool(boolean theValue) {
        value = theValue;
    }
    
    @Override
    public boolean equals(Object o) {
        if (getClass() == o.getClass()) {
            Bool other = (Bool) o;
            return value == other.value;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + (this.value ? 1 : 0);
        return hash;
    }
    
}
