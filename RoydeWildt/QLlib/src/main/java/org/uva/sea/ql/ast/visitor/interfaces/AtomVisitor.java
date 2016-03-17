package org.uva.sea.ql.ast.visitor.interfaces;

import org.uva.sea.ql.ast.tree.atom.val.Bool;
import org.uva.sea.ql.ast.tree.atom.val.Float;
import org.uva.sea.ql.ast.tree.atom.val.Int;
import org.uva.sea.ql.ast.tree.atom.val.Str;
import org.uva.sea.ql.ast.tree.atom.var.Var;


/**
 * Created by roy on 5-2-16.
 */
public interface AtomVisitor<ATOM,CONTEXT> {

    ATOM visit(Bool atom, CONTEXT context);
    ATOM visit(Int atom, CONTEXT context);
    ATOM visit(Float atom, CONTEXT context);
    ATOM visit(Str atom, CONTEXT context);
    ATOM visit(Var atom, CONTEXT context);

}
