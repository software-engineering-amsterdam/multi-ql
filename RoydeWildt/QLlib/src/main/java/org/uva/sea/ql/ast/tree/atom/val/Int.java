package org.uva.sea.ql.ast.tree.atom.val;

import org.antlr.v4.runtime.Token;
import org.uva.sea.ql.ast.visitor.interfaces.AtomVisitor;

/**
 * Created by roydewildt on 04/02/16.
 */
public class Int extends Val {
    private Integer value;

    public Int(Token token, String x){
        super(token);
        this.value = Integer.valueOf(x);
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public <ATOM, CONTEXT> ATOM accept(AtomVisitor<ATOM, CONTEXT> visitor, CONTEXT context) {
        return visitor.visit(this,context);
    }
}
