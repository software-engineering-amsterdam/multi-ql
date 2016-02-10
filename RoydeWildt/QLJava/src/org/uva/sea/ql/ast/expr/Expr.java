package org.uva.sea.ql.ast.expr;


import org.uva.sea.ql.ast.Node;
import org.uva.sea.ql.ast.checker.Visitable;
import org.uva.sea.ql.ast.checker.Visitor;

import java.util.List;

/**
 * Created by roydewildt on 04/02/16.
 */
abstract public class Expr implements Node, Visitable {

    public Expr() {
    }
}
