package org.uva.sea.ql.ast.visitor.interfaces;

/**
 * Created by roy on 5-2-16.
 */
public interface IFormVisitable {

    <F,C> F accept(IFormVisitor<F,C> visitor, C context);
}
