package org.uva.sea.ql.ast.expr;

/**
 * Representation of <code>Expr</code>s which compare two other <code>Expr</code>s
 * in some way for which these operands need to be ordered in an AST.
 * 
 * @author Olav Trauschke
 * @version 24-feb-2016
 */
public class OrderedComparisonExpr extends ComparisonExpr {
    
    /**
     * Constructor for objects of class <code>OrderedComparisonExpr</code>.
     * 
     * @param firstExpr the <code>Expr</code> on the left hand side of the operator
     * @param secondExpr the <code>Expr</code> on the right hand side of the operator
     */
    public OrderedComparisonExpr(Expr firstExpr, Expr secondExpr) {
        super(firstExpr, secondExpr);
    }
    
}
