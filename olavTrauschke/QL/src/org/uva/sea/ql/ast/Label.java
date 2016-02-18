package org.uva.sea.ql.ast;

import org.uva.sea.ql.ast.expr.Str;

public class Label extends ASTNode {
    
    private final String text;
    
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
        return 79 * 3 + text.hashCode();
    }
}
