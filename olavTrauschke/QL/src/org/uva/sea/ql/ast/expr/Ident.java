package org.uva.sea.ql.ast.expr;

public class Ident extends Expr {
    
    private final String content;
    
    public Ident(String theContent) {
        assert theContent != null;
        content = theContent;
    }
    
    @Override
    public boolean equals(Object o) {
        return o != null
                && getClass() == o.getClass()
                && content.equals(((Ident) o).content);
    }

    @Override
    public int hashCode() {
        return 97 * 3 + content.hashCode();
    }
    
}
