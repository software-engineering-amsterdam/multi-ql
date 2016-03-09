package org.uva.sea.ql.ast.visitor.interfaces;

import org.uva.sea.ql.ast.tree.val.Bool;
import org.uva.sea.ql.ast.tree.val.Int;
import org.uva.sea.ql.ast.tree.val.Str;
import org.uva.sea.ql.ast.tree.var.Var;


/**
 * Created by roy on 5-2-16.
 */
public interface ValVisitor<VAL,CONTEXT> {

    VAL visit(Bool val, CONTEXT context);
    VAL visit(Int val, CONTEXT context);
    VAL visit(Str val, CONTEXT context);

}
