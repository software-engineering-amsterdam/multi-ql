package org.uva.sea.ql.ast.stat;

import org.uva.sea.ql.ast.visitor.Visitor;
import org.uva.sea.ql.ast.expr.Expr;
import org.uva.sea.ql.ast.val.Val;
import org.uva.sea.ql.ast.var.Var;

/**
 * Created by roydewildt on 04/02/16.
 */
public class Question extends Stat {
    private String label;
    private Var varname;
    private Val type;

    public Question(int line, String label, Var varname, Val type){
        super(line);
        this.label = label;
        this.varname = varname;
        this.type = type;
    }

    public <T> T accept(Visitor visitor) {
        return visitor.visit(this);
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

}
