package org.uva.sea.ql.ast.tree.val;

import org.uva.sea.ql.ast.tree.Node;
import org.uva.sea.ql.ast.visitor.interfaces.IValVisitable;

/**
 * Created by roydewildt on 04/02/16.
 */
abstract public class Val extends Node implements IValVisitable {

    public Val(int line){
        super(line);
    }
    public abstract Object getValue();
}
