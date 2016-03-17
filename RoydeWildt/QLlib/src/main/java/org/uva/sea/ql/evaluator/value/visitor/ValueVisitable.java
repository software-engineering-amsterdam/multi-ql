package org.uva.sea.ql.evaluator.value.visitor;

/**
 * Created by roydewildt on 16/03/16.
 */
public interface ValueVisitable {

    <ATOM,CONTEXT> ATOM accept(ValueVisitor<ATOM,CONTEXT> visitor, CONTEXT context);

}
