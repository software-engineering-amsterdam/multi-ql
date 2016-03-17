package org.uva.sea.ql.ast.tree.atom.val;

import org.antlr.v4.runtime.Token;
import org.uva.sea.ql.ast.tree.atom.Literal;

/**
 * Created by roydewildt on 04/02/16.
 */
abstract public class Val extends Literal {

    public Val(Token token){
        super(token);
    }
    public abstract Object getValue();
}
