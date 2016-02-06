package org.uva.sea.ql.ast.expr;

import java.util.Objects;

public class Not extends BooleanExpr {
    
    private BooleanExpr content;
    
    public Not(Expr theContent) {
        content = (BooleanExpr) theContent;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof Not) {
            Not other = (Not) o;
            return content.equals(other.content);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.content);
        return hash;
    }
    
}
