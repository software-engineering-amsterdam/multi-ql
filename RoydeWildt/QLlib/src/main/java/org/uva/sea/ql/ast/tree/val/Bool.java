package org.uva.sea.ql.ast.tree.val;

import org.uva.sea.ql.ast.visitor.interfaces.ValVisitor;

/**
 * Created by roydewildt on 04/02/16.
 */
public class Bool extends Val {
    private Boolean value;

    public Bool(int line, boolean b){
        super(line);
        this.value = b;
    }

    public Bool(int line, String x){
        super(line);
        try{
            this.value = Boolean.valueOf(x);
        }

        catch (Exception e){
            System.out.println("Log: invalid value, expected a boolean");
        }
    }

    public Boolean getValue() {
        return value;
    }

    public static Bool defaultValue(int line){
        return new Bool(line,false);
    }


    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public <S, C> S accept(ValVisitor<S, C> visitor, C context) {
        return visitor.visit(this, context);
    }
}
