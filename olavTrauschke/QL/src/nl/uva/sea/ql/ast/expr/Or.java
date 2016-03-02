package nl.uva.sea.ql.ast.expr;

/**
 * Representation of boolean disjunction in an AST.
 * 
 * @author Olav Trauschke
 * @version 24-feb-2016
 */
public class Or extends BooleanConjunctiveExpr {
    
    /**
     * Constructor for objects of class <code>Or</code>.
     * 
     * @param firstExpr the <code>Expr</code> on the left hand side of the disjunction
     * @param secondExpr the <code>Expr</code> on the right hand side of the disjunction
     */
    public Or(Expr firstExpr, Expr secondExpr) {
        super(firstExpr, secondExpr);
    }
    
}
