package org.uva.sea.ql.evaluator.value.visitor;


import org.uva.sea.ql.evaluator.value.Bool;
import org.uva.sea.ql.evaluator.value.String;
import org.uva.sea.ql.evaluator.value.numeric.Int;
import org.uva.sea.ql.evaluator.value.numeric.Double;

/**
 * Created by roydewildt on 16/03/16.
 */
public interface ValueVisitor<VALUE,CONTEXT> {

    VALUE visit(Bool atom, CONTEXT context);
    VALUE visit(String atom, CONTEXT context);
    VALUE visit(Double atom, CONTEXT context);
    VALUE visit(Int atom, CONTEXT context);
}
