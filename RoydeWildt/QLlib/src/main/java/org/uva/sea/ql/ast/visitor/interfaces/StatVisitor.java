package org.uva.sea.ql.ast.visitor.interfaces;

import org.uva.sea.ql.ast.tree.stat.If;
import org.uva.sea.ql.ast.tree.stat.IfElse;
import org.uva.sea.ql.ast.tree.stat.Question;


/**
 * Created by roy on 5-2-16.
 */
public interface StatVisitor<STAT,CONTEXT> {

    STAT visit(If stat, CONTEXT context);
    STAT visit(IfElse stat, CONTEXT context);
    STAT visit(Question stat, CONTEXT context);

}
