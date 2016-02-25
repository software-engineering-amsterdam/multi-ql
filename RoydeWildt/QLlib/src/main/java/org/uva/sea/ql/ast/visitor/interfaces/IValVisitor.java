package org.uva.sea.ql.ast.visitor.interfaces;

import org.uva.sea.ql.ast.tree.val.Bool;
import org.uva.sea.ql.ast.tree.val.Int;
import org.uva.sea.ql.ast.tree.val.Var;


/**
 * Created by roy on 5-2-16.
 */
public interface IValVisitor<T> {

    T visit(Bool val);
    T visit(Int val);
    T visit(Var var);

}
