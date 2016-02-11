package org.uva.sea.ql.ast.stat;

import org.uva.sea.ql.ast.expr.Expr;
import org.uva.sea.ql.ast.val.Val;
import org.uva.sea.ql.ast.var.Var;

/**
 * Created by roydewildt on 11/02/16.
 */
public class AssQuestion extends Question {

    private Expr expr;

    public AssQuestion(int line, String label, Var varname, Val type, Expr expr) {
        super(line, label, varname, type);
        this.expr = expr;
    }

    public Expr getExpr() {
        return expr;
    }
}
