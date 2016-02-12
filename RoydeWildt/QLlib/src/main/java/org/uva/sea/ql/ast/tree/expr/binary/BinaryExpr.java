package org.uva.sea.ql.ast.tree.expr.binary;

import org.uva.sea.ql.ast.tree.expr.Expr;

/**
 * Created by roydewildt on 10/02/16.
 */
public abstract class BinaryExpr extends Expr {
    private Expr lhs;
    private Expr rhs;

    public BinaryExpr(int line, Expr lhs, Expr rhs) {
        super(line);
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(lhs.toString());
        sb.append(' ');
        sb.append(this.getSymbol());
        sb.append(' ');
        sb.append(rhs.toString());
        return sb.toString();
    }

    public Expr getLhs() {
        return lhs;
    }

    public Expr getRhs() {
        return rhs;
    }
}
