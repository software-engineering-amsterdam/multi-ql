package org.uva.sea.ql.ast.expr;

import java.util.Objects;

public class Ident extends Expr {
    
    private String content;
    
    public Ident(String theContent) {
        super(true, true, true);
        content = theContent;
    }
    
    @Override
    public boolean equals(Object o) {
        if (super.equals(o)) {
            Ident other = (Ident) o;
            return content.equals(other.content);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 59 * hash + Objects.hashCode(this.content);
        return hash;
    }
    
}
