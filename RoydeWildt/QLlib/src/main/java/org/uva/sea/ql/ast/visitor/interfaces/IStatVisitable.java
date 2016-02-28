package org.uva.sea.ql.ast.visitor.interfaces;

/**
 * Created by roy on 5-2-16.
 */
public interface IStatVisitable {

    <S,C> S accept(IStatVisitor<S,C> visitor, C context);
}
