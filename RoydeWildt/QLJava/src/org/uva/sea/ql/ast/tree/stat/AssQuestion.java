package org.uva.sea.ql.ast.tree.stat;

import org.uva.sea.ql.ast.tree.expr.Expr;
import org.uva.sea.ql.ast.tree.type.Type;
import org.uva.sea.ql.ast.tree.var.Var;

/**
 * Created by roydewildt on 11/02/16.
 */
public class AssQuestion extends Question {

    private Expr expr;

    public AssQuestion(int line, String label, Var varname, Type type, Expr expr) {
        super(line, label, varname, type);
        this.expr = expr;
    }

    public Expr getExpr() {
        return expr;
    }
}
