package org.uva.sea.ql.ast.visitor.interfaces;

/**
 * Created by roy on 5-2-16.
 */
public interface IValVisitable {

    <V,C> V accept(ValVisitor<V,C> visitor, C context);
}
