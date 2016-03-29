package org.uva.sea.ql.evaluator.adt.visitor;


import org.uva.sea.ql.evaluator.adt.value.Bool;
import org.uva.sea.ql.evaluator.adt.value.Str;
import org.uva.sea.ql.evaluator.adt.value.numeric.Double;
import org.uva.sea.ql.evaluator.adt.value.numeric.Int;

/**
 * Created by roydewildt on 16/03/16.
 */
public interface ValueVisitor<VALUE,CONTEXT> {

    VALUE visit(Bool atom, CONTEXT context);
    VALUE visit(Str atom, CONTEXT context);
    VALUE visit(Double atom, CONTEXT context);
    VALUE visit(Int atom, CONTEXT context);
}
