package nl.uva.sea.ql.ast.expr;

import nl.uva.sea.ql.ASTVisitor;
import nl.uva.sea.ql.answerTable.AnswerTable;
import nl.uva.sea.ql.answerTable.NumericValue;

/**
 * Representation of division in an AST.
 * 
 * @author Olav Trauschke
 * @version 17-mrt-2016
 */
public class Div extends BinaryNumericOperatorExpr {
    
    /**
     * Constructor for objects of class <code>Div</code>.
     * 
     * @param firstExpr the <code>Expr</code> on the left hand side of the operator
     * @param secondExpr the <code>Expr</code> on the right hand side of the operator
     */
    public Div(Expr firstExpr, Expr secondExpr) {
        super(firstExpr, secondExpr);
    }
    
    /**
     * Overrides
     * {@link nl.uva.sea.ql.ast.expr.BinaryNumericOperatorExpr#accept(nl.uva.sea.ql.checker.ASTVisitor)
     * BinaryNumericOperatorExpr.accept(ASTVisitor)} for local dynamic dispatch
     * only.
     * 
     * @param visitor an <code>ASTVisitor</code> that should
     *                  <code>visit this BinaryNumericOperatorExpr</code> and
     *                  its children
     */
    @Override
    public void accept(ASTVisitor visitor) {
        childrenAccept(visitor);
        
        visitor.visit(this);
    }
    
    @Override
    public NumericValue eval(AnswerTable answerTable) {
        NumericValue firstValue = (NumericValue) getFirstExpr().eval(answerTable);
        NumericValue secondValue = (NumericValue) getSecondExpr().eval(answerTable);
        return firstValue.divide(secondValue);
    }
}
