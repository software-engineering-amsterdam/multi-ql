package ql.ast.expression;

/**
 *
 * @author sander
 */
public class BinaryExpr extends Expr {

    private final Expr _Lhs, _Rhs;

    public BinaryExpr(Expr lhs, Expr rhs) {
        this._Lhs = lhs;
        this._Rhs = rhs;
    }

    public Expr getLhs() {
        return this._Lhs;
    }

    public Expr getRhs() {
        return this._Rhs;
    }
}
