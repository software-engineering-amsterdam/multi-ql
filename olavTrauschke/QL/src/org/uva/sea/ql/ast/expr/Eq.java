package org.uva.sea.ql.ast.expr;

/**
 * Representation of equality comparisons in an AST.
 * 
 * @author Olav Trauschke
 * @version 24-feb-2016
 */
public class Eq extends ComparisonExpr {
    
    /**
     * Constructor for objects of class <code>Eq</code>.
     * 
     * @param firstExpr the <code>Expr</code> on the left hand side of the operator
     * @param secondExpr the <code>Expr</code> on the right hand side of the operator
     */
    public Eq(Expr firstExpr, Expr secondExpr) {
        super(firstExpr, secondExpr);
    }
    
}
