package org.uva.sea.ql.ast.tree.type;

import org.uva.sea.ql.ast.tree.atom.val.numeric.Int;
import org.uva.sea.ql.ast.visitor.interfaces.TypeVisitor;

/**
 * Created by roydewildt on 11/02/16.
 */
public class Number extends Type {
    private Int defaultValue;

    public Number(){
        super(0);
    }

    public Number(int line) {
        super(line);
        this.defaultValue = new Int().getDefaultValue(line);
    }

    public Type getType(){return new Number();}

    public Int getDefaultValue() {
        return defaultValue;
    }

    @Override
    public <TYPE, CONTEXT> TYPE accept(TypeVisitor<TYPE, CONTEXT> visitor, CONTEXT context) {
        return visitor.visit(this, context);
    }
}
