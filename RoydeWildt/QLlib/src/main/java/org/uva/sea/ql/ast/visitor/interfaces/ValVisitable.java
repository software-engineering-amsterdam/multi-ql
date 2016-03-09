package org.uva.sea.ql.ast.visitor.interfaces;

/**
 * Created by roy on 5-2-16.
 */
public interface ValVisitable {

    <VAL,CONTEXT> VAL accept(ValVisitor<VAL,CONTEXT> visitor, CONTEXT context);
}
