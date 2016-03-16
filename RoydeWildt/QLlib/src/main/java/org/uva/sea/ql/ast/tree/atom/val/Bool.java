package org.uva.sea.ql.ast.tree.atom.val;

import org.uva.sea.ql.ast.visitor.interfaces.AtomVisitor;

/**
 * Created by roydewildt on 04/02/16.
 */
public class Bool extends Val {
    private Boolean value;

    public Bool(){
        super(0);
    }

    public Bool(boolean b){
        super(0);
        this.value = b;
    }

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

    @Override
    public Bool getDefaultValue(int line){
        return new Bool(line,false);
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
