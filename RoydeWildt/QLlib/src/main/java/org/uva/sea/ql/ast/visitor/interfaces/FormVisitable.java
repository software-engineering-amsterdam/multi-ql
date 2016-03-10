package org.uva.sea.ql.ast.visitor.interfaces;

/**
 * Created by roy on 5-2-16.
 */
public interface FormVisitable {

    <FORM, CONTEXT> FORM accept(FormVisitor<FORM, CONTEXT> visitor, CONTEXT context);
}
