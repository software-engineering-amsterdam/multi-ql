package org.uva.sea.ql.ast.expr;

public abstract class SingleExprArgumentNumericExpr extends NumericExpr {
    
    private final Expr content;
    
    public SingleExprArgumentNumericExpr(Expr theContent) {
        assert theContent != null;
        content = theContent;
    }
    
    @Override
    public boolean equals(Object o) {
        return o != null
                && getClass() == o.getClass()
                && content.equals(((SingleExprArgumentNumericExpr) o).content);
    }

    @Override
    public int hashCode() {
        return 31 * 5 + content.hashCode();
    }
    
}
