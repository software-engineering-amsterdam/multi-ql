package nl.uva.sea.ql.ast.expr;

/**
 * Representation of "greater than or equal to" comparisons in an AST.
 * 
 * @author Olav Trauschke
 * @version 24-feb-2016
 */
public class GEq extends OrderedComparisonExpr {
    
    /**
     * Constructor for objects of class <code>GEq</code>.
     * 
     * @param firstExpr the <code>Expr</code> on the left hand side of the operator
     * @param secondExpr the <code>Expr</code> on the right hand side of the operator
     */
    public GEq(Expr firstExpr, Expr secondExpr) {
        super(firstExpr, secondExpr);
    }
    
}
