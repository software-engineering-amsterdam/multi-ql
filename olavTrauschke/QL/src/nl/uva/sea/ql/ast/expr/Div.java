package nl.uva.sea.ql.ast.expr;

import nl.uva.sea.ql.answerTable.AnswerTable;
import nl.uva.sea.ql.answerTable.NumericValue;
import nl.uva.sea.ql.generalPurposeVisitors.ASTVisitor;

/**
 * Representation of division in an AST.
 * 
 * @author Olav Trauschke
 * @version 26-mrt-2016
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
     * {@link nl.uva.sea.ql.ast.expr.BinaryNumericOperatorExpr#accept(nl.uva.sea.ql.generalPurposeVisitors.ASTVisitor)
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
    
    /**
     * Evaluate <code>this Div</code>.
     * 
     * @param answerTable an <code>AnswerTable</code> mapping all <code>Ident</code>s
     *                      that might appear in <code>Expr</code>s in
     *                      <code>this Div</code> to the <code>Value</code> of
     *                      the <code>Question</code> they represent
     * @return a <code>NumericValue</code> representing the result of dividing
     *          the <code>firstExpr</code> of <code>this Div</code> by its
     *          <code>secondFactor</code>
     */
    @Override
    public NumericValue eval(AnswerTable answerTable) {
        NumericValue firstValue = NumericValue.cast(getFirstExpr().eval(answerTable));
        NumericValue secondValue = NumericValue.cast(getSecondExpr().eval(answerTable));
        return firstValue.divide(secondValue);
    }
}
