package ql.ast.expression;

/**
 *
 * @author sander
 */
public class BinaryExpr extends Expr {

    private final Expr lhs, rhs;

    public BinaryExpr(Expr lhs, Expr rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public Expr getLhs() {
        return this.lhs;
    }

    public Expr getRhs() {
        return this.rhs;
    }
}
