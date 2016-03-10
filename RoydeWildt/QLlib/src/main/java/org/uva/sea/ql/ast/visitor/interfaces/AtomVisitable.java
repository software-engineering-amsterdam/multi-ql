package org.uva.sea.ql.ast.visitor.interfaces;

/**
 * Created by roy on 5-2-16.
 */
public interface AtomVisitable {

    <ATOM,CONTEXT> ATOM accept(AtomVisitor<ATOM,CONTEXT> visitor, CONTEXT context);
}
