package org.uva.sea.ql.ast.tree.atom.val;

import org.uva.sea.ql.ast.visitor.interfaces.AtomVisitor;

/**
 * Created by roydewildt on 04/02/16.
 */
public class Int extends Val {
    private Integer value;

    public Int(){
        super(0);
    }

    public Int(Integer i){
        super(0);
        this.value = i;
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
    public Int getDefaultValue(){
        return new Int(0);
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
