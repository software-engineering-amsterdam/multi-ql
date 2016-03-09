package org.uva.sea.ql.ast.visitor.interfaces;

/**
 * Created by roy on 5-2-16.
 */
public interface TypeVisitable {

    <TYPE,CONTEXT> TYPE accept(TypeVisitor<TYPE,CONTEXT> visitor, CONTEXT context);
}
