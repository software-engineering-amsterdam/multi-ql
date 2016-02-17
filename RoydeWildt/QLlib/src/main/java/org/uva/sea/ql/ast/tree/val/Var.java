package org.uva.sea.ql.ast.tree.val;

import org.uva.sea.ql.ast.tree.Node;
import org.uva.sea.ql.ast.visitor.Visitor;

/**
 * Created by roydewildt on 04/02/16.
 */
public class Var extends Val {
    private String value;
    public Var(int line, String value) {
        super(line);
        this.value = value;}

    public <T,U> T accept(Visitor<T,U> visitor, U context) {
        return visitor.visit(this, context);
    }

    @Override
    public String toString() {
        return value;
    }

    public String getValue() {
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

}
