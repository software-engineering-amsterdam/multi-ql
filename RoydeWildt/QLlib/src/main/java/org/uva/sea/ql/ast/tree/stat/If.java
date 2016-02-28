package org.uva.sea.ql.ast.tree.stat;

import org.uva.sea.ql.ast.tree.expr.Expr;
import org.uva.sea.ql.ast.visitor.interfaces.IStatVisitor;

import java.util.List;

/**
 * Created by roydewildt on 04/02/16.
 */
public class If extends Stat{

    Expr cond;
    List<Stat> stms;

    public If (int line, Expr cond, List<Stat> stms){
        super(line);
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
    public <S, C> S accept(IStatVisitor<S, C> visitor, C context) {
        return visitor.visit(this, context);
    }

    public Expr getCond() {
        return cond;
    }

    public List<Stat> getStms() {
        return stms;
    }
}
