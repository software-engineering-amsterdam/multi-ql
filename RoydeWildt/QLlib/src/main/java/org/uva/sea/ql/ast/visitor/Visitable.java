package org.uva.sea.ql.ast.visitor;

/**
 * Created by roy on 5-2-16.
 */
public interface Visitable {

    <T> T accept(Visitor visitor);

}
