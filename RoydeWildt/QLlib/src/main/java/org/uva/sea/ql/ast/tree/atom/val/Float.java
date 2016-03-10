package org.uva.sea.ql.ast.tree.atom.val;

import org.uva.sea.ql.ast.visitor.interfaces.AtomVisitor;

/**
 * Created by roydewildt on 04/02/16.
 */
public class Float extends Val {
    private Double value;

    public Float(int line, Double i){
        super(line);
        this.value = i;}

    public Float(int line, String x){
        super(line);

        try{
            this.value = Double.valueOf(x);
        }
        catch (Exception e){
            System.out.println("Log: invalid value, expected an float");
        }
    }

    public Double getValue() {
        return value;
    }

    public static Float defaultValue(int line){
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
