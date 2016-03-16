package org.uva.sea.ql.ast.tree.atom.val;

import org.uva.sea.ql.ast.visitor.interfaces.AtomVisitor;

/**
 * Created by roydewildt on 04/02/16.
 */
public class Float extends Val {
    private Double value;

    public Float(){
        super(0);
    }

    public Float(Double i){
        super(0);
        this.value = i;
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
    public Float getDefaultValue(){
        return new Float(0.0);
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
