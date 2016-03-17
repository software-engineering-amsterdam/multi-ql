package org.uva.sea.ql.ast.tree.atom.val;

import org.antlr.v4.runtime.Token;
import org.uva.sea.ql.ast.visitor.interfaces.AtomVisitor;

/**
 * Created by roydewildt on 09/03/16.
 */
public class Str extends Val{
    private String value;

    public Str(Token token, String value) {
        super(token);
        this.value = value.replace("\"", "");
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public <ATOM, CONTEXT> ATOM accept(AtomVisitor<ATOM, CONTEXT> visitor, CONTEXT context) {
        return visitor.visit(this,context);
    }
}
