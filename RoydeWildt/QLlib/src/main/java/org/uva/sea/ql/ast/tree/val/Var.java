package org.uva.sea.ql.ast.tree.val;

import org.uva.sea.ql.ast.visitor.interfaces.ValVisitor;

/**
 * Created by roydewildt on 04/02/16.
 */
public class  Var extends Val {
    private final String value;

    public Var(int line, String value) {
        super(line);
        this.value = value;
    }

    public String getName() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Var){
            Var toCompare = (Var) obj;
            return this.value.equals(toCompare.value);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public <S, C> S accept(ValVisitor<S, C> visitor, C context) {
        return visitor.visit(this, context);
    }

    @Override
    public Object getValue() {
        return null;
    }
}
