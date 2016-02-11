package org.uva.sea.ql.ast.expr;

import java.util.Objects;

public abstract class SingleExprArgumentNumericExpr extends NumericExpr {
    
    private Expr content;
    
    public SingleExprArgumentNumericExpr(Expr theContent) {
        if (theContent.canBeNumeric()) {
            content = theContent;
        }
        else {
            throwNonNumericOperandsException();
        }
    }
    
    @Override
    public boolean equals(Object o) {
        if (getClass() == o.getClass()) {
            SingleExprArgumentNumericExpr other = (SingleExprArgumentNumericExpr) o;
            return content.equals(other.content);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.content);
        return hash;
    }
    
}
