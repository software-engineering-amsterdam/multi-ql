package org.uva.sea.ql.ast.tree.atom;

import org.antlr.v4.runtime.Token;
import org.uva.sea.ql.ast.tree.Node;
import org.uva.sea.ql.ast.visitor.interfaces.AtomVisitable;
import org.uva.sea.ql.ast.visitor.interfaces.AtomVisitor;

/**
 * Created by roy on 3/10/16.
 */
public abstract class Literal extends Node implements AtomVisitable{

    public Literal(Token token) {
        super(token);
    }

    @Override
    public abstract <ATOM, CONTEXT> ATOM accept(AtomVisitor<ATOM, CONTEXT> visitor, CONTEXT context);

    @Override
    public abstract String toString();
}
