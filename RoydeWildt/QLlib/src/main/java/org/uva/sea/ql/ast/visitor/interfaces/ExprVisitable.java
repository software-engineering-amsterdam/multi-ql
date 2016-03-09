package org.uva.sea.ql.ast.visitor.interfaces;

/**
 * Created by roy on 5-2-16.
 */
public interface ExprVisitable {

    <EXPR,CONTEXT> EXPR accept(ExprVisitor<EXPR,CONTEXT> visitor, CONTEXT context);
}
