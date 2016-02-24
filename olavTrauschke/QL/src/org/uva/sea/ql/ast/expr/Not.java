package org.uva.sea.ql.ast.expr;

public class Not extends BooleanExpr {
    
    public static final int HASH_ORIGIN = 65;
    
    private final Expr content;
    
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
        return HASH_ORIGIN + content.hashCode();
    }
    
}
