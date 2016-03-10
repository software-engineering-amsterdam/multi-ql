package org.uva.sea.ql.ast.tree.atom.val;

import org.uva.sea.ql.ast.visitor.interfaces.AtomVisitor;

/**
 * Created by roydewildt on 09/03/16.
 */
public class Str extends Val{
    private String value;

    public Str(int line, String value) {
        super(line);
        this.value = value.replace("\"", "");
    }

    @Override
    public Object getValue() {
        return value;
    }

    public static Str defaultValue(int line){
        return new Str(line,"");
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
