package org.uva.sea.ql.ast.tree.atom.val.numeric;

import org.uva.sea.ql.ast.visitor.interfaces.AtomVisitor;

/**
 * Created by roydewildt on 04/02/16.
 */
public class Float extends Numeric {
    private Double value;

    public Float(){
        super(0);
    }

    public Float(int line, Double i){
        super(line);
        this.value = i;
    }

    public Float(String x){
        super(0);
        this.value = Double.valueOf(x);
    }

    public Float(int line, String x){
        super(line);
        this.value = Double.valueOf(x);
    }

    @Override
    public Double getValue() {
        return value;
    }

    @Override
    public Float Add(Numeric x) {
        if(x instanceof Float){
            return new Float(this.getLine(), this.value + ((Float) x).getValue());
        }
        else if (x instanceof Int){
            return new Float(this.getLine(), this.value + ((Int) x).getValue());
        }
        throw new ClassCastException();
    }

    @Override
    public Float Div(Numeric x) {
        if (x.getValue().equals(0)){
            throw new ClassCastException();
        }
        if(x instanceof Float){
            return new Float(this.getLine(), this.value / ((Float) x).getValue());
        }
        else if (x instanceof Int){
            return new Float(this.getLine(), this.value / ((Int) x).getValue());
        }
        throw new ClassCastException();
    }

    @Override
    public Float Mul(Numeric x) {
        if(x instanceof Float){
            return new Float(this.getLine(), this.value * ((Float) x).getValue());
        }
        else if (x instanceof Int){
            return new Float(this.getLine(), this.value * ((Int) x).getValue());
        }
        throw new ClassCastException();
    }

    @Override
    public Float Sub(Numeric x) {
        if(x instanceof Float){
            return new Float(this.getLine(), this.value - ((Float) x).getValue());
        }
        else if (x instanceof Int){
            return new Float(this.getLine(), this.value - ((Int) x).getValue());
        }
        throw new ClassCastException();
    }

    @Override
    public Float Pos() {
        return new Float(this.getLine(), this.value);
    }

    @Override
    public Float Neg() {
        return new Float(this.getLine(), -this.value);
    }

    @Override
    public Float getDefaultValue(int line){
        return new Float(line, 0.0);
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public <ATOM, CONTEXT> ATOM accept(AtomVisitor<ATOM, CONTEXT> visitor, CONTEXT context) {
        return visitor.visit(this,context);
    }
}
