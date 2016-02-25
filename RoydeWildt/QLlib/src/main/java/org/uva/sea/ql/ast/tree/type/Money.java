package org.uva.sea.ql.ast.tree.type;

import org.uva.sea.ql.ast.tree.val.Int;
import org.uva.sea.ql.ast.tree.val.Val;
import org.uva.sea.ql.ast.type.MoneyType;
import org.uva.sea.ql.ast.type.ValueType;
import org.uva.sea.ql.ast.visitor.interfaces.ITypeVisitor;

/**
 * Created by roydewildt on 11/02/16.
 */
public class Money extends Type {
    public ValueType getType(){return new MoneyType();}

    @Override
    public Val defaultValue() {
        return new Int(0);
    }

    public Money(int line) {
        super(line);
    }


    @Override
    public <T> T accept(ITypeVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
