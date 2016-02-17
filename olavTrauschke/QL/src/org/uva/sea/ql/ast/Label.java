package org.uva.sea.ql.ast;

public class Label extends ASTNode {
    
    private final String text;
    
    public Label(String theText) {
        assert theText != null;
        text = theText;
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
