package org.uva.sea.ql.ast.tree.stat.decl;

import org.antlr.v4.runtime.Token;
import org.uva.sea.ql.ast.tree.atom.var.Var;
import org.uva.sea.ql.ast.tree.expr.Expr;
import org.uva.sea.ql.ast.tree.stat.Stat;
import org.uva.sea.ql.ast.tree.type.Type;
import org.uva.sea.ql.ast.visitor.interfaces.StatVisitor;

/**
 * Created by roydewildt on 04/02/16.
 */
public class Computed extends Declaration {
    private final Expr expr;

    public Computed(Token token, String label, Var varname, Type type, Expr expr){
        super(token, label, type, varname);
        this.expr = expr;
    }

    public Expr getExpr() {
        return expr;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("EvaluatedQuestion(");
        sb.append(this.getLabel() + ",");
        sb.append(this.getVarname() + ",");
        sb.append(this.getType() + ",");
        sb.append(")");
        return sb.toString();
    }

    @Override
    public <STAT, CONTEXT> STAT accept(StatVisitor<STAT, CONTEXT> visitor, CONTEXT context) {
        return visitor.visit(this, context);
    }
}
