package org.uva.sea.ql.ast.tree.stat.block;

import org.antlr.v4.runtime.Token;
import org.uva.sea.ql.ast.tree.expr.Expr;
import org.uva.sea.ql.ast.tree.stat.Stat;
import org.uva.sea.ql.ast.visitor.interfaces.StatVisitor;

import java.util.List;

/**
 * Created by roydewildt on 04/02/16.
 */
public class IfElse extends Block {

    private final List<Stat> ifStms;
    private final List<Stat> elseStms;

    public IfElse (Token token, Expr cond, List<Stat> ifStms, List<Stat> elseStms){
        super(token, cond);
        this.ifStms = ifStms;
        this.elseStms = elseStms;
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
    public <STAT, CONTEXT> STAT accept(StatVisitor<STAT, CONTEXT> visitor, CONTEXT context) {
        return visitor.visit(this, context);
    }
}
