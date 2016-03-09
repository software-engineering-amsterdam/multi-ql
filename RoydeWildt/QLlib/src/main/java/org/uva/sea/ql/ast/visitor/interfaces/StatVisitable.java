package org.uva.sea.ql.ast.visitor.interfaces;

/**
 * Created by roy on 5-2-16.
 */
public interface StatVisitable {

    <STAT,CONTEXT> STAT accept(StatVisitor<STAT,CONTEXT> visitor, CONTEXT context);
}
