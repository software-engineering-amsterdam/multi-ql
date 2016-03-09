package org.uva.sea.ql.ast.tree.var;

import org.uva.sea.ql.ast.tree.Node;
import org.uva.sea.ql.ast.tree.val.Val;
import org.uva.sea.ql.ast.visitor.interfaces.ValVisitor;
import org.uva.sea.ql.ast.visitor.interfaces.VarVisitable;
import org.uva.sea.ql.ast.visitor.interfaces.VarVisitor;

/**
 * Created by roydewildt on 04/02/16.
 */
public class  Var extends Node implements VarVisitable {
    private final String value;

    public Var(int line, String value) {
        super(line);
        this.value = value;
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
    public <S, C> S accept(VarVisitor<S, C> visitor, C context) {
        return visitor.visit(this, context);
    }

}
