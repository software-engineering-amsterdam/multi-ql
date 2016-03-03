package nl.uva.sea.ql.ast.expr;

/**
 * Representation of "less than" comparisons in an AST.
 * 
 * @author Olav Trauschke
 * @version 24-feb-2016
 */
public class LT extends OrderedComparisonExpr {
    
    /**
     * Constructor for objects of class <code>LT</code>.
     * 
     * @param firstExpr the <code>Expr</code> on the left hand side of the operator
     * @param secondExpr the <code>Expr</code> on the right hand side of the operator
     */
    public LT(Expr firstExpr, Expr secondExpr) {
        super(firstExpr, secondExpr);
    }
    
}
