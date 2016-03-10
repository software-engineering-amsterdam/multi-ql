package org.uva.sea.ql.ast.tree.type;

import org.uva.sea.ql.ast.tree.atom.val.numeric.Float;
import org.uva.sea.ql.ast.visitor.interfaces.TypeVisitor;

/**
 * Created by roydewildt on 11/02/16.
 */
public class Money extends Type {
    private Float defaultValue;

    public Money(){
        super(0);
    }

    public Money(int line) {
        super(line);
        this.defaultValue = new Float().getDefaultValue(line);
    }

    public Type getType(){return new Money();}

    public Float getDefaultValue() {
        return defaultValue;
    }

    @Override
    public <TYPE, CONTEXT> TYPE accept(TypeVisitor<TYPE, CONTEXT> visitor, CONTEXT context) {
        return visitor.visit(this, context);
    }
}
