package org.uva.sea.ql.ast.visitor.interfaces;

/**
 * Created by roy on 5-2-16.
 */
public interface IStatVisitable {

    <S> S accept(IStatVisitor<S> visitor);
}
