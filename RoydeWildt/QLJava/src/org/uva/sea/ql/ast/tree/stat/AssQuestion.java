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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Question(");
        sb.append(this.getLabel() + ",");
        sb.append(this.getVarname() + ",");
        sb.append(this.getType() + ",");
        sb.append(expr.toString());
        sb.append(")");
        return sb.toString();
    }

    public Expr getExpr() {
        return expr;
    }
}
