package org.uva.sea.ql.ast.visitor.interfaces;

/**
 * Created by roy on 5-2-16.
 */
public interface TypeVisitable {

    <T,C> T accept(TypeVisitor<T,C> visitor, C context);
}
