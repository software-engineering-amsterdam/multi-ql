package org.uva.sea.ql.ast.expr;

/**
 * Representation of inverse equality comparisons in an AST.
 * 
 * @author Olav Trauschke
 * @version 24-feb-2016
 */
public class NEq extends ComparisonExpr {
    
    /**
     * Constructor for objects of class <code>NEq</code>.
     * 
     * @param firstExpr the <code>Expr</code> on the left hand side of the operator
     * @param secondExpr the <code>Expr</code> on the right hand side of the operator
     */
    public NEq(Expr firstExpr, Expr secondExpr) {
        super(firstExpr, secondExpr);
    }
    
}
