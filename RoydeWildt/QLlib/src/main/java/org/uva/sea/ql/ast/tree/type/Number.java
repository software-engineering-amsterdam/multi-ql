package org.uva.sea.ql.ast.tree.type;

import org.antlr.v4.runtime.Token;
import org.uva.sea.ql.ast.tree.atom.val.Int;
import org.uva.sea.ql.ast.visitor.interfaces.TypeVisitor;

/**
 * Created by roydewildt on 11/02/16.
 */
public class Number extends Type {

    public Number(Token token) {
        super(token);
    }

    @Override
    public <TYPE, CONTEXT> TYPE accept(TypeVisitor<TYPE, CONTEXT> visitor, CONTEXT context) {
        return visitor.visit(this, context);
    }
}
