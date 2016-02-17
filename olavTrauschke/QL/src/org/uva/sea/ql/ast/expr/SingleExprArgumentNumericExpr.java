package org.uva.sea.ql.ast.expr;

import java.util.Objects;

public abstract class SingleExprArgumentNumericExpr extends NumericExpr {
    
    private Expr content;
    
    public SingleExprArgumentNumericExpr(Expr theContent) {
        if (theContent.canBeNumeric()) {
            content = theContent;
        }
        else {
            setError(TYPE_ERROR_MESSAGE);
        }
    }
    
    @Override
    public boolean equals(Object o) {
        return super.equals(o) && content.equals(((SingleExprArgumentNumericExpr) o).content);
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 23 * hash + Objects.hashCode(this.content);
        return hash;
    }
    
}
