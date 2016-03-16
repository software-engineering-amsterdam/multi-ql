package org.uva.sea.ql.evaluator.value.numeric;

import org.uva.sea.ql.evaluator.value.Bool;
import org.uva.sea.ql.evaluator.value.Value;

/**
 * Created by roydewildt on 16/03/16.
 */
public abstract class Numeric extends Value {

    public abstract Numeric Add(Numeric x);
    public abstract Numeric Div(Numeric x);
    public abstract Numeric Mul(Numeric x);
    public abstract Numeric Sub(Numeric x);

    public abstract Bool GT(Numeric x);
    public abstract Bool GEq(Numeric x);
    public abstract Bool LEq(Numeric x);
    public abstract Bool LT(Numeric x);

    public abstract Numeric Pos();
    public abstract Numeric Neg();
}
