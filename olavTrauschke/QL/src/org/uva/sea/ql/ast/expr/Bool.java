package org.uva.sea.ql.ast.expr;

public class Bool extends BooleanExpr {
    
    private boolean value;
    
    public Bool(boolean theValue) {
        value = theValue;
    }
    
    @Override
    public boolean equals(Object o) {
        return super.equals(o) && value == ((Bool) o).value;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 13 * hash + (this.value ? 1 : 0);
        return hash;
    }
    
}
