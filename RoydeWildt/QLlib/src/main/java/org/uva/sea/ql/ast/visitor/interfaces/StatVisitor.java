package org.uva.sea.ql.ast.visitor.interfaces;

import org.uva.sea.ql.ast.tree.stat.block.If;
import org.uva.sea.ql.ast.tree.stat.block.IfElse;
import org.uva.sea.ql.ast.tree.stat.decl.Computed;
import org.uva.sea.ql.ast.tree.stat.decl.Question;


/**
 * Created by roy on 5-2-16.
 */
public interface StatVisitor<STAT,CONTEXT> {

    STAT visit(If stat, CONTEXT context);
    STAT visit(IfElse stat, CONTEXT context);
    STAT visit(Question stat, CONTEXT context);
    STAT visit(Computed stat, CONTEXT context);

}
