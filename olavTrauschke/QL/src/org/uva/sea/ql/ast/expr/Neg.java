package org.uva.sea.ql.ast.expr;

/**
 * Representation of a a minus in front of a number in an AST.
 * 
 * @author Olav Trauschke
 * @version 24-feb-2016
 */
public class Neg extends UnaryNumericOperatorExpr {
    
    /**
     * Constructor for objects of class <code>Neg</code>.
     * 
     * @param content the <code>Expr</code> after the operator
     */
    public Neg(Expr content) {
        super(content);
    }
}
