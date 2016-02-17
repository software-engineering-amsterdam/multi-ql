package org.uva.sea.ql.ast.expr;

public class Not extends BooleanExpr {
    
    private Expr content;
    
    public Not(Expr theContent) {
        assert theContent != null;
        content = theContent;
    }
    
    @Override
    public boolean equals(Object o) {
        return o != null
                && getClass() == o.getClass()
                && content.equals(((Not) o).content);
    }

    @Override
    public int hashCode() {
        return 13 * 5 + content.hashCode();
    }
    
}
