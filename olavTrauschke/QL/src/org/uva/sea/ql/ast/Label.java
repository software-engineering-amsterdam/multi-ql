package org.uva.sea.ql.ast;

import org.uva.sea.ql.ast.expr.Str;

public class Label extends ASTNode {
    
    public static final int HASH_ORIGIN = 237;
    
    private final String text;
    
    public Label(String theText) {
        assert theText != null;
        text = theText;
    }
    
    public Label(Str theText) {
        assert theText != null;
        text = theText.getValue();
    }
    
    @Override
    public boolean equals(Object o) {
        return o != null
                && getClass() == o.getClass()
                && text.equals(((Label) o).text);
    }

    @Override
    public int hashCode() {
        return HASH_ORIGIN + text.hashCode();
    }
}
