package org.uva.sea.ql.evaluator.value.numeric;

import org.uva.sea.ql.evaluator.value.Bool;

/**
 * Created by roydewildt on 16/03/16.
 */
public class Int extends Numeric {
    private Integer value;

    public Int(Integer value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public Int Add(Numeric x) {
        return new Int(this.value + (int) x.getValue());
    }

    @Override
    public Int Div(Numeric x) {
        return new Int(this.value / (int) x.getValue());
    }

    @Override
    public Int Mul(Numeric x) {
        return new Int(this.value * (int) x.getValue());
    }

    @Override
    public Int Sub(Numeric x) {
        return new Int(this.value - (int) x.getValue());
    }

    @Override
    public Bool LT(Numeric x) {
        return new Bool(this.value < (int) x.getValue());
    }

    @Override
    public Bool LEq(Numeric x) {
        return new Bool(this.value <= (int) x.getValue());
    }

    @Override
    public Bool GEq(Numeric x) {
        return new Bool(this.value >= (int) x.getValue());
    }

    @Override
    public Bool GT(Numeric x) {
        return new Bool(this.value > (int) x.getValue());
    }

    @Override
    public Int Pos() {
        return new Int(this.value);
    }

    @Override
    public Int Neg() {
        return new Int(-this.value);
    }
}
