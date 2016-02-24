package org.uva.sea.ql.ast.tree.type;

import org.uva.sea.ql.ast.tree.val.Bool;
import org.uva.sea.ql.ast.tree.val.Val;
import org.uva.sea.ql.ast.type.BooleanType;
import org.uva.sea.ql.ast.type.ValueType;
import org.uva.sea.ql.ast.visitor.Visitor;

/**
 * Created by roydewildt on 11/02/16.
 */
public class Boolean extends Type {
    public ValueType getType(){return new BooleanType();}

    @Override
    public Val defaultValue() {
        return new Bool(false);
    }

    public Boolean(int line) {
        super(line);
    }

    public <T,U> T accept(Visitor<T,U> visitor, U context) {
        return visitor.visit(this, context);
    }
}
