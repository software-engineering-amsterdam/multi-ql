package org.uva.sea.ql.evaluator.value.numeric;

import org.uva.sea.ql.evaluator.value.Bool;
import org.uva.sea.ql.evaluator.value.visitor.ValueVisitor;

/**
 * Created by roydewildt on 16/03/16.
 */
public class Double extends Numeric {
    private java.lang.Double value;

    public Double(java.lang.Double value) {
        this.value = value;
    }

    @Override
    public java.lang.Double getValue() {
        return value;
    }

    @Override
    public Double Add(Numeric x) {
        return new Double(this.value + (double) x.getValue());
    }

    @Override
    public Double Div(Numeric x) {
        return new Double(this.value / (double) x.getValue());
    }

    @Override
    public Double Mul(Numeric x) {
        return new Double(this.value * (double) x.getValue());
    }

    @Override
    public Double Sub(Numeric x) {
        return new Double(this.value - (double) x.getValue());
    }

    @Override
    public Bool LT(Numeric x) {
        return new Bool(this.value < (double) x.getValue());
    }

    @Override
    public Bool LEq(Numeric x) {
        return new Bool(this.value <= (double) x.getValue());
    }

    @Override
    public Bool GEq(Numeric x) {
        return new Bool(this.value >= (double) x.getValue());
    }

    @Override
    public Bool GT(Numeric x) {
        return new Bool(this.value > (double) x.getValue());
    }

    @Override
    public Double Pos() {
        return new Double(this.value);
    }

    @Override
    public Double Neg() {
        return new Double(-this.value);
    }

    @Override
    public <ATOM, CONTEXT> ATOM accept(ValueVisitor<ATOM, CONTEXT> visitor, CONTEXT context) {
        return visitor.visit(this, context);
    }
}
