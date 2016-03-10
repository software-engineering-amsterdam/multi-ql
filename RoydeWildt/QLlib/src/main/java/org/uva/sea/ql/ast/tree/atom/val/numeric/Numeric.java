package org.uva.sea.ql.ast.tree.atom.val.numeric;

import org.uva.sea.ql.ast.tree.atom.val.Val;

/**
 * Created by roy on 3/10/16.
 */
public abstract class Numeric extends Val {

    public Numeric(int line) {
        super(line);
    }

    public abstract Numeric Add(Numeric x);
    public abstract Numeric Div(Numeric x);
    public abstract Numeric Mul(Numeric x);
    public abstract Numeric Sub(Numeric x);

    public abstract Numeric Pos();
    public abstract Numeric Neg();
}
