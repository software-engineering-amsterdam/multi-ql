package org.uva.sea.ql.ast.tree.type;

import org.uva.sea.ql.ast.tree.atom.val.Bool;
import org.uva.sea.ql.ast.visitor.interfaces.TypeVisitor;

/**
 * Created by roydewildt on 11/02/16.
 */
public class Boolean extends Type {
    public Bool defaultValue;

    public Boolean() {
        super(0);
    }

    public Boolean(int line) {
        super(line);
        this.defaultValue = Bool.defaultValue(line);
    }

    public Type getType(){return new Boolean();}

    public Bool getDefaultValue() {
        return defaultValue;
    }

    @Override
    public <TYPE, CONTEXT> TYPE accept(TypeVisitor<TYPE, CONTEXT> visitor, CONTEXT context) {
        return visitor.visit(this, context);
    }
}
