package org.uva.sea.ql.ast.tree.type;

import org.uva.sea.ql.ast.tree.val.Int;
import org.uva.sea.ql.ast.visitor.interfaces.TypeVisitor;

/**
 * Created by roydewildt on 11/02/16.
 */
public class Money extends Type {
    private Int defaultValue;

    public Money(){
        super(0);
    }

    public Money(int line) {
        super(line);
        this.defaultValue = Int.defaultValue(line);
    }

    public Type getType(){return new Money();}

    public Int getDefaultValue() {
        return defaultValue;
    }

    @Override
    public <S, C> S accept(TypeVisitor<S, C> visitor, C context) {
        return visitor.visit(this, context);
    }
}
