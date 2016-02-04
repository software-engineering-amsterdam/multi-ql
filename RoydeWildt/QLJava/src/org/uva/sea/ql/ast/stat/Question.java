package org.uva.sea.ql.ast.stat;

import org.uva.sea.ql.ast.expr.Expr;

/**
 * Created by roydewildt on 04/02/16.
 */
public class Question extends Stat{
    private String label;
    private String varname;
    private String type;
    private Expr   expr;

    public Question(String label, String varname, String type){
        this.label = label;
        this.varname = varname;
        this.type = type;
    }

    public Question(String label, String varname, String type, Expr expr){
        this.label = label;
        this.varname = varname;
        this.type = type;
        this.expr = expr;
    }

    @Override
    public String toString() {
        if(expr != null)
            return "Question(" + label + ", " + varname  + ", " + type  + ", " + expr.toString() + ")";
        else
            return "Question(" + label + ", " + varname  + ", " + type + ")";
    }
}
