package org.uva.sea.ql.ast.tree.stat;

import org.uva.sea.ql.ast.tree.expr.Expr;
import org.uva.sea.ql.ast.visitor.interfaces.IStatVisitor;

import java.util.List;

/**
 * Created by roydewildt on 04/02/16.
 */
public class IfElse extends Stat{

    private final Expr cond;
    private final List<Stat> ifStms;
    private final List<Stat> elseStms;

    public IfElse (int line, Expr cond, List<Stat> ifStms, List<Stat> elseStms){
        super(line);
        this.cond = cond;
        this.ifStms = ifStms;
        this.elseStms = elseStms;
    }

    public Expr getCond() {
        return cond;
    }

    public List<Stat> getIfStms() {
        return ifStms;
    }

    public List<Stat> getElseStms() {
        return elseStms;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("If(");
        sb.append(cond.toString() + ",");
        sb.append(ifStms.toString() + ",");
        sb.append(elseStms.toString() + ",");
        sb.append(")");
        return sb.toString();
    }

    @Override
    public <S, C> S accept(IStatVisitor<S, C> visitor, C context) {
        return visitor.visit(this, context);
    }
}
