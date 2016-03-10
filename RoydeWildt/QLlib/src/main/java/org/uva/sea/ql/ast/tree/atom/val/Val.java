package org.uva.sea.ql.ast.tree.atom.val;

import org.uva.sea.ql.ast.tree.atom.Literal;

/**
 * Created by roydewildt on 04/02/16.
 */
abstract public class Val extends Literal {

    public Val(int line){
        super(line);
    }
    public abstract Object getValue();
}
