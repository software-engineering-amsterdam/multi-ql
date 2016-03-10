package org.uva.sea.ql.ast.tree.type;

import org.uva.sea.ql.ast.tree.atom.val.Float;
import org.uva.sea.ql.ast.tree.atom.val.Int;
import org.uva.sea.ql.ast.visitor.interfaces.TypeVisitor;

/**
 * Created by roydewildt on 11/02/16.
 */
public class Number extends Type {
    private Float defaultValue;

    public Number(){
        super(0);
    }

    public Number(int line) {
        super(line);
        this.defaultValue = Float.defaultValue(line);
    }

    public Type getType(){return new Number();}

    public Float getDefaultValue() {
        return defaultValue;
    }

    @Override
    public <TYPE, CONTEXT> TYPE accept(TypeVisitor<TYPE, CONTEXT> visitor, CONTEXT context) {
        return visitor.visit(this, context);
    }
}
