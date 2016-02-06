package org.uva.sea.ql.ast.expr;

public class Int extends NumericExpr {
    
    private int content;
    
    public Int(int theContent) {
        content = theContent;
    }
    
    @Override
    public boolean equals(Object o) {
        if (getClass().equals(o.getClass())) {
            Int other = (Int) o;
            return content == other.content;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + this.content;
        return hash;
    }
}
