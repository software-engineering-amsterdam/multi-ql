package org.uva.sea.ql.ast.tree.val;

import org.uva.sea.ql.ast.visitor.interfaces.IValVisitor;

/**
 * Created by roydewildt on 04/02/16.
 */
public class  Var extends Val {
    private String value;

    public Var(int line, String value) {
        super(line);
        this.value = value;
    }

    public String getValue() {
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
    public <V> V accept(IValVisitor<V> visitor) {
        return visitor.visit(this);
    }
}
