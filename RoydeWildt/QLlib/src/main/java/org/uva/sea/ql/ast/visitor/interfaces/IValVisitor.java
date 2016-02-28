package org.uva.sea.ql.ast.visitor.interfaces;

import org.uva.sea.ql.ast.tree.val.Bool;
import org.uva.sea.ql.ast.tree.val.Int;
import org.uva.sea.ql.ast.tree.val.Var;


/**
 * Created by roy on 5-2-16.
 */
public interface IValVisitor<T,C> {

    T visit(Bool val, C context);
    T visit(Int val, C context);
    T visit(Var var, C context);

}
