package org.uva.sea.ql.ast.expr;

/**
 * Representation of boolean conjunction in an AST.
 * 
 * @author Olav Trauschke, 10329463
 * @version 24-feb-2016
 */
public class And extends BooleanConjunctiveExpr {
    
    /**
     * Constructor for objects of class <code>And</code>.
     * 
     * @param firstExpr the <code>Expr</code> on the left hand side of the conjunction
     * @param secondExpr the <code>Expr</code> on the right hand side of the conjunction
     */
    public And(Expr firstExpr, Expr secondExpr) {
        super(firstExpr, secondExpr);
    }
    
}
