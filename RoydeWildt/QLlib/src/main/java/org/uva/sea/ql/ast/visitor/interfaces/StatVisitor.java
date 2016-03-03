package org.uva.sea.ql.ast.visitor.interfaces;

import org.uva.sea.ql.ast.tree.stat.If;
import org.uva.sea.ql.ast.tree.stat.IfElse;
import org.uva.sea.ql.ast.tree.stat.Question;


/**
 * Created by roy on 5-2-16.
 */
public interface StatVisitor<T,C> {

    T visit(If stat, C context);
    T visit(IfElse stat, C context);
    T visit(Question stat, C context);

}
