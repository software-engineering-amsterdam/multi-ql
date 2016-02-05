package org.uva.sea.ql.ast.expr;

import org.uva.sea.ql.ast.val.Val;

/**
 * Created by roydewildt on 04/02/16.
 */
public class Prim extends Expr{
    Val v;

    public Prim(Val v) {
        this.v = v;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "(" + v.toString() + ")";
    }

    public Val getV() {
        return v;
    }
}
