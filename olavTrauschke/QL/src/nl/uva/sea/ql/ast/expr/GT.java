package nl.uva.sea.ql.ast.expr;

/**
 * Representation of "greater than" comparisons in an AST.
 * 
 * @author Olav Trauschke
 * @version 16-mar-2016
 */
public class GT extends OrderedComparisonExpr {
    
    /**
     * Constructor for objects of class <code>GT</code>.
     * 
     * @param firstExpr the <code>Expr</code> on the left hand side of the operator
     * @param secondExpr the <code>Expr</code> on the right hand side of the operator
     */
    public GT(Expr firstExpr, Expr secondExpr) {
        super(firstExpr, secondExpr);
    }
    
}
