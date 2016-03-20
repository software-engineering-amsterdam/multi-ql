package org.uva.sea.ql.ast.tree.stat.decl;

import org.antlr.v4.runtime.Token;
import org.uva.sea.ql.ast.tree.atom.var.Var;
import org.uva.sea.ql.ast.tree.expr.Expr;
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
        return "EvaluatedQuestion(" +
                this.getLabel() + "," +
                this.getVarname() + "," +
                this.getType() + "," +
                ")";
    }

    @Override
    public <STAT, CONTEXT> STAT accept(StatVisitor<STAT, CONTEXT> visitor, CONTEXT context) {
        return visitor.visit(this, context);
    }
}
