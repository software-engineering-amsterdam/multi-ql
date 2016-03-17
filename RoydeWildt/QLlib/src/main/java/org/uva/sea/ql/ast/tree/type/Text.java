package org.uva.sea.ql.ast.tree.type;

import org.antlr.v4.runtime.Token;
import org.uva.sea.ql.ast.tree.atom.val.Str;
import org.uva.sea.ql.ast.tree.atom.val.Val;
import org.uva.sea.ql.ast.visitor.interfaces.TypeVisitor;

/**
 * Created by roydewildt on 09/03/16.
 */
public class Text extends Type {

    public Text(Token token) {
        super(token);
    }

    @Override
    public <TYPE, CONTEXT> TYPE accept(TypeVisitor<TYPE, CONTEXT> visitor, CONTEXT context) {
        return visitor.visit(this, context);
    }
}
