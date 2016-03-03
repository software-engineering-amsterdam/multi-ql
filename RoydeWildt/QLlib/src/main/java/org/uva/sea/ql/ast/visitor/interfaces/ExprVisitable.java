package org.uva.sea.ql.ast.visitor.interfaces;

/**
 * Created by roy on 5-2-16.
 */
public interface ExprVisitable {

    <E,C> E accept(ExprVisitor<E,C> visitor, C context);
}
