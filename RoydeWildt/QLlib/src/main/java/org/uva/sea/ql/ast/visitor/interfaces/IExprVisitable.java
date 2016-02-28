package org.uva.sea.ql.ast.visitor.interfaces;

/**
 * Created by roy on 5-2-16.
 */
public interface IExprVisitable {

    <E,C> E accept(IExprVisitor<E,C> visitor, C context);
}
