package nl.uva.sea.ql.ast.expr;

import nl.uva.sea.ql.ASTVisitor;

/**
 * Representation of <code>Expr</code>s which compare two other <code>Expr</code>s
 * in some way for which these operands need to be ordered in an AST.
 * 
 * @author Olav Trauschke
 * @version 25-feb-2016
 */
public abstract class OrderedComparisonExpr extends ComparisonExpr {
    
    /**
     * Constructor for objects of class <code>OrderedComparisonExpr</code>.
     * 
     * @param firstExpr the <code>Expr</code> on the left hand side of the operator
     * @param secondExpr the <code>Expr</code> on the right hand side of the operator
     */
    public OrderedComparisonExpr(Expr firstExpr, Expr secondExpr) {
        super(firstExpr, secondExpr);
    }
    
    /**
     * Has the <code>firstExpr</code> and the <code>secondExpr</code> of
     * <code>this OrderedComparisonExpr accept visitor</code> and then has
     * <code>visitor visit this OrderedComparisonExpr</code>.
     * 
     * @param visitor an <code>ASTVisitor</code> that should
     *          <code>visit this OrderedComparisonExpr</code> and its children
     */
    @Override
    public void accept(ASTVisitor visitor) {
        firstExprAccept(visitor);
        secondExprAccept(visitor);
        
        visitor.visit(this);
    }
    
}
