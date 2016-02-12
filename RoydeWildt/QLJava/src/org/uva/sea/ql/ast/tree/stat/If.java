package org.uva.sea.ql.ast.tree.stat;

import org.uva.sea.ql.ast.visitor.Visitor;
import org.uva.sea.ql.ast.tree.expr.Expr;

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

    public <T> T accept(Visitor visitor) {
        return visitor.visit(this);
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

    public Expr getCond() {
        return cond;
    }

    public List<Stat> getStms() {
        return stms;
    }
}
