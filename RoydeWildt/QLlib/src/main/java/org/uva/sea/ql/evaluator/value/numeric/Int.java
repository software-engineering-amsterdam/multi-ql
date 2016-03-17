package org.uva.sea.ql.evaluator.value.numeric;

import org.uva.sea.ql.evaluator.value.Bool;
import org.uva.sea.ql.evaluator.value.visitor.ValueVisitor;

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

        if(x instanceof Int)
            return new Int(this.value + (Integer) x.getValue());
        else
            return null;
    }

    @Override
    public Int Div(Numeric x) {


        if(x instanceof Int)
            return new Int(this.value / (Integer) x.getValue());
        else
            return null;
    }

    @Override
    public Int Mul(Numeric x) {


        if(x instanceof Int)
            return new Int(this.value * (Integer) x.getValue());
        else
            return null;
    }

    @Override
    public Int Sub(Numeric x) {

        if(x instanceof Int)
            return new Int(this.value - (Integer) x.getValue());
        else
            return null;
    }

    @Override
    public Bool LT(Numeric x) {
        if(x instanceof Int)
            return new Bool(this.value < (Integer) x.getValue());
        else
            return null;
    }

    @Override
    public Bool LEq(Numeric x) {

        if(x instanceof Int)
            return new Bool(this.value <= (Integer) x.getValue());
        else
            return null;
    }

    @Override
    public Bool GEq(Numeric x) {

        if(x instanceof Int)
            return new Bool(this.value >= (Integer) x.getValue());
        else
            return null;
    }

    @Override
    public Bool GT(Numeric x) {

        if(x instanceof Int)
            return new Bool(this.value > (Integer) x.getValue());
        else
            return null;
    }

    @Override
    public Int Pos() {
        return new Int(this.value);
    }

    @Override
    public Int Neg() {
        return new Int(-this.value);
    }

    @Override
    public <ATOM, CONTEXT> ATOM accept(ValueVisitor<ATOM, CONTEXT> visitor, CONTEXT context) {
        return visitor.visit(this, context);
    }
}
