package org.uva.sea.ql.ast.tree.atom.val.numeric;

import org.uva.sea.ql.ast.visitor.interfaces.AtomVisitor;

/**
 * Created by roydewildt on 04/02/16.
 */
public class Int extends Numeric {
    private Integer value;

    public Int(){
        super(0);
    }

    public Int(int line, int i){
        super(line);
        this.value = i;
    }

    public Int(String x){
        super(0);
        this.value = Integer.valueOf(x);
    }

    public Int(int line, String x){
        super(line);
        this.value = Integer.valueOf(x);
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public Int Add(Numeric x) {
        if(x instanceof Int){
            return new Int(this.getLine(), this.value + (Integer) x.getValue());
        }
        throw new ClassCastException();
    }

    @Override
    public Int Div(Numeric x) {
        throw new ClassCastException();
    }

    @Override
    public Int Mul(Numeric x) {
        throw new ClassCastException();
    }

    @Override
    public Int Sub(Numeric x) {
        if(x instanceof Int){
            return new Int(this.getLine(), this.value - (Integer) x.getValue());
        }
        throw new ClassCastException();
    }

    @Override
    public Int Pos() {
        return new Int(this.getLine(), this.getValue());
    }

    @Override
    public Int Neg() {
        throw new ClassCastException();
    }

    @Override
    public Int getDefaultValue(int line){
        return new Int(line,0);
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
