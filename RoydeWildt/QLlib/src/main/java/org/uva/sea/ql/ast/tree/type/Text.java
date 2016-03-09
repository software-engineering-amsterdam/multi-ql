package org.uva.sea.ql.ast.tree.type;

import org.uva.sea.ql.ast.tree.val.Str;
import org.uva.sea.ql.ast.tree.val.Val;
import org.uva.sea.ql.ast.type.TextType;
import org.uva.sea.ql.ast.type.ValueType;
import org.uva.sea.ql.ast.visitor.interfaces.TypeVisitor;

/**
 * Created by roydewildt on 09/03/16.
 */
public class Text extends Type {
    private Str defaultValue;

    public Text(int line) {
        super(line);
        this.defaultValue = Str.defaultValue(line);
    }

    @Override
    public ValueType getType() {
        return new TextType();
    }

    @Override
    public Val getDefaultValue() {
        return defaultValue;
    }

    @Override
    public <T, C> T accept(TypeVisitor<T, C> visitor, C context) {
        return null;
    }
}
