package org.uva.sea.ql.ast.tree.atom.val;

import org.uva.sea.ql.ast.visitor.interfaces.AtomVisitor;

/**
 * Created by roydewildt on 04/02/16.
 */
public class Int extends Val {
    private Integer value;

    public Int(int line, int i){
        super(line);
        this.value = i;}

    public Int(int line, String x){
        super(line);

        try{
            this.value = Integer.valueOf(x);
        }
        catch (Exception e){
            System.out.println("Log: invalid value, expected an integer");
        }
    }

    public Integer getValue() {
        return value;
    }

    public static Int defaultValue(int line){
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
