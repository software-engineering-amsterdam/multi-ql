package org.uva.sea.ql.ast.tree.atom;

import org.uva.sea.ql.ast.tree.Node;
import org.uva.sea.ql.ast.visitor.interfaces.AtomVisitable;
import org.uva.sea.ql.ast.visitor.interfaces.AtomVisitor;

/**
 * Created by roy on 3/10/16.
 */
public abstract class Literal extends Node implements AtomVisitable{

    public Literal(int line) {
        super(line);
    }

    @Override
    public abstract <ATOM, CONTEXT> ATOM accept(AtomVisitor<ATOM, CONTEXT> visitor, CONTEXT context);

    @Override
    public abstract String toString();
}
