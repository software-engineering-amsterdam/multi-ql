package org.uva.sea.ql.ast.visitor.interfaces;

/**
 * Created by roy on 5-2-16.
 */
public interface FormVisitable {

    <F,C> F accept(FormVisitor<F,C> visitor, C context);
}
