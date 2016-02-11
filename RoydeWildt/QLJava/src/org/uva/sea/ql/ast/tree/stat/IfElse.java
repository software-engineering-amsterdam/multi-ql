package org.uva.sea.ql.ast.tree.stat;

import org.uva.sea.ql.ast.visitor.Visitor;
import org.uva.sea.ql.ast.tree.expr.Expr;

import java.util.List;

/**
 * Created by roydewildt on 04/02/16.
 */
public class IfElse extends Stat{

    Expr cond;
    List<Stat> ifStms;
    List<Stat> elseStms;

    public IfElse (int line, Expr cond, List<Stat> ifStms, List<Stat> elseStms){
        super(line);
        this.cond = cond;
        this.ifStms = ifStms;
        this.elseStms = elseStms;
    }

    public <T> T accept(Visitor visitor) {
        return visitor.visit(this);
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
}
