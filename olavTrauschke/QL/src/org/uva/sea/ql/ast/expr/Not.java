package org.uva.sea.ql.ast.expr;

import java.util.Objects;

public class Not extends BooleanExpr {
    
    public static final String TYPE_ERROR_MESSAGE = "Can not negate non-boolean operand";
    
    private Expr content;
    
    public Not(Expr theContent) {
        if (theContent.canBeBoolean()) {
            content = theContent;
        }
        else {
            setError(TYPE_ERROR_MESSAGE);
        }
    }
    
    @Override
    public boolean equals(Object o) {
        return super.equals(o) && content.equals(((Not) o).content);
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 41 * hash + Objects.hashCode(this.content);
        return hash;
    }
    
}
