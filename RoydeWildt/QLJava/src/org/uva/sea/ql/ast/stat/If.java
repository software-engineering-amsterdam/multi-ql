package org.uva.sea.ql.ast.stat;

import org.uva.sea.ql.ast.visitor.Visitor;
import org.uva.sea.ql.ast.expr.Expr;

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

    public Expr getCond() {
        return cond;
    }

    public List<Stat> getStms() {
        return stms;
    }
}
