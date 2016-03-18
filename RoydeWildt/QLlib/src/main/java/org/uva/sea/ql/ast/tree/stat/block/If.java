package org.uva.sea.ql.ast.tree.stat.block;

import org.antlr.v4.runtime.Token;
import org.uva.sea.ql.ast.tree.expr.Expr;
import org.uva.sea.ql.ast.tree.stat.Stat;
import org.uva.sea.ql.ast.visitor.interfaces.StatVisitor;

import java.util.List;

/**
 * Created by roydewildt on 04/02/16.
 */
public class If extends Block {

    private final List<Stat> stms;

    public If (Token token, Expr cond, List<Stat> stms){
        super(token, cond);
        this.stms = stms;
    }

    public List<Stat> getStms() {
        return stms;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("If(");
        sb.append(getCond().toString() + ",");
        sb.append(stms.toString());
        sb.append(")");
        return sb.toString();
    }

    @Override
    public <STAT, CONTEXT> STAT accept(StatVisitor<STAT, CONTEXT> visitor, CONTEXT context) {
        return visitor.visit(this, context);
    }

}
