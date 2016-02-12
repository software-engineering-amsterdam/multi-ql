package org.uva.sea.ql.ast.tree.val;

import org.uva.sea.ql.ast.tree.Node;
import org.uva.sea.ql.ast.visitor.Visitor;

/**
 * Created by roydewildt on 04/02/16.
 */
abstract public class Val extends Node {

    public Val(int line){
        super(line);
    }
}
