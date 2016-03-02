package nl.uva.sea.ql.ast.expr;

/**
 * Representation of multiplication in an AST.
 * 
 * @author Olav Trauschke
 * @version 24-feb-2016
 */
public class Mul extends BinaryNumericOperatorExpr {
    
    /**
     * Constructor for objects of class <code>Mul</code>.
     * 
     * @param firstExpr the <code>Expr</code> on the left hand side of the operator
     * @param secondExpr the <code>Expr</code> on the right hand side of the operator
     */
    public Mul(Expr firstExpr, Expr secondExpr) {
        super(firstExpr, secondExpr);
    }
}
