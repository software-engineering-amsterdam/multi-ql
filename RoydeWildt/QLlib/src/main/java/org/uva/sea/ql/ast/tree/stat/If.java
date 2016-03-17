package org.uva.sea.ql.ast.tree.stat;

import org.antlr.v4.runtime.Token;
import org.uva.sea.ql.ast.tree.expr.Expr;
import org.uva.sea.ql.ast.visitor.interfaces.StatVisitor;

import java.util.List;

/**
 * Created by roydewildt on 04/02/16.
 */
public class If extends Stat{

    private final Expr cond;
    private final List<Stat> stms;

    public If (Token token, Expr cond, List<Stat> stms){
        super(token);
        this.cond = cond;
        this.stms = stms;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("If(");
        sb.append(cond.toString() + ",");
        sb.append(stms.toString());
        sb.append(")");
        return sb.toString();
    }

    @Override
    public <STAT, CONTEXT> STAT accept(StatVisitor<STAT, CONTEXT> visitor, CONTEXT context) {
        return visitor.visit(this, context);
    }

    public Expr getCond() {
        return cond;
    }

    public List<Stat> getStms() {
        return stms;
    }
}
