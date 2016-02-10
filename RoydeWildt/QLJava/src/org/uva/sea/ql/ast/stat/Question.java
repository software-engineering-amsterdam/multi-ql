package org.uva.sea.ql.ast.stat;

import org.uva.sea.ql.ast.Node;
import org.uva.sea.ql.ast.checker.Visitor;
import org.uva.sea.ql.ast.expr.Expr;
import org.uva.sea.ql.ast.val.Val;
import org.uva.sea.ql.ast.var.Var;

import java.util.List;

/**
 * Created by roydewildt on 04/02/16.
 */
public class Question extends Stat {
    private String label;
    private Var varname;
    private Val type;
    private Expr   expr;

    public Question(String label, Var varname, Val type){
        this.label = label;
        this.varname = varname;
        this.type = type;
    }

    public Question(String label, Var varname, Val type, Expr expr){
        this.label = label;
        this.varname = varname;
        this.type = type;
        this.expr = expr;
    }

    public <T> T accept(Visitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        if(expr != null)
            return "Question(" + label + ", " + varname + ", " + type  + ", " + expr.toString() + ")";
        else
            return "Question(" + label + ", " + varname  + ", " + type + ")";
    }

    public String getLabel() {
        return label;
    }

    public Var getVarname() {
        return varname;
    }

    public Val getType() {
        return type;
    }

    public Expr getExpr() {
        return expr;
    }
}
