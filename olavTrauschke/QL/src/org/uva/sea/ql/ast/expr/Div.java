package org.uva.sea.ql.ast.expr;

/**
 * Representation of division in an AST.
 * 
 * @author Olav Trauschke, 10329463
 * @version 24-feb-2016
 */
public class Div extends DoubleArgumentNumericExpr {
    
    /**
     * Constructor for objects of class <code>Div</code>.
     * 
     * @param firstExpr the <code>Expr</code> on the left hand side of the operator
     * @param secondExpr the <code>Expr</code> on the right hand side of the operator
     */
    public Div(Expr firstExpr, Expr secondExpr) {
        super(firstExpr, secondExpr);
    }
}
