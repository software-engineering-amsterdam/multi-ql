package org.uva.sea.ql.ast.visitor;

/**
 * Created by roy on 5-2-16.
 */
public interface Visitable {

    <T,U> T accept(Visitor<T, U> visitor, U context);
}
