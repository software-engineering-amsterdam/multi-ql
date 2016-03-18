package org.uva.sea.ql.ast.tree.stat.block;

import org.antlr.v4.runtime.Token;
import org.uva.sea.ql.ast.tree.expr.Expr;
import org.uva.sea.ql.ast.tree.stat.Stat;

/**
 * Created by roy on 3/18/16.
 */
public abstract class Block extends Stat {
    protected final Expr cond;

    public Block(Token token, Expr cond) {
        super(token);
        this.cond = cond;
    }

    public Expr getCond() {
        return cond;
    }
}
