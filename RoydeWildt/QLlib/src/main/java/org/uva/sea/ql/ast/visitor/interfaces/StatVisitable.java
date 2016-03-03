package org.uva.sea.ql.ast.visitor.interfaces;

/**
 * Created by roy on 5-2-16.
 */
public interface StatVisitable {

    <S,C> S accept(StatVisitor<S,C> visitor, C context);
}
