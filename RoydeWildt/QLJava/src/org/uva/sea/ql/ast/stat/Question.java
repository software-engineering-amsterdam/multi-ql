package org.uva.sea.ql.ast.stat;

import org.uva.sea.ql.ast.expr.Expr;

/**
 * Created by roydewildt on 04/02/16.
 */
public class Question extends Stat{

    public Question(String label, String varname, String type){
        super(label, varname, type);
    }

    public Question(String label, String varname, String type, Expr expr){
        super(label, varname, type, expr);
    }

    @Override
    public String toString() {
        if(getExpr() != null)
            return "Question(" + getLabel() + ", " + getVarname()  + ", " + getType()  + ", " + getExpr().toString() + ")";
        else
            return "Question(" + getLabel() + ", " + getVarname()  + ", " + getType() + ")";
    }
}
