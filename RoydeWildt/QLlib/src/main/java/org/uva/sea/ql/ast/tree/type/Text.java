package org.uva.sea.ql.ast.tree.type;

import org.uva.sea.ql.ast.tree.atom.val.Str;
import org.uva.sea.ql.ast.tree.atom.val.Val;
import org.uva.sea.ql.ast.visitor.interfaces.TypeVisitor;

/**
 * Created by roydewildt on 09/03/16.
 */
public class Text extends Type {
    private Str defaultValue;

    public Text(){
        super(0);
    }

    public Text(int line) {
        super(line);
        this.defaultValue = new Str().getDefaultValue();
    }

    @Override
    public Type getType() {
        return new Text();
    }

    @Override
    public Val getDefaultValue() {
        return defaultValue;
    }

    @Override
    public <TYPE, CONTEXT> TYPE accept(TypeVisitor<TYPE, CONTEXT> visitor, CONTEXT context) {
        return visitor.visit(this, context);
    }
}
