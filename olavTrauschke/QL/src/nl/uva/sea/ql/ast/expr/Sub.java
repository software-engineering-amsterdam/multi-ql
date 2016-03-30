package nl.uva.sea.ql.ast.expr;

import nl.uva.sea.ql.answerTable.*;

/**
 * Representation of substraction in an AST.
 * 
 * @author Olav Trauschke
 * @version 26-mar-2016
 */
public class Sub extends BinaryNumericOperatorExpr {
    
    /**
     * Constructor for objects of class <code>Sub</code>.
     * 
     * @param firstExpr the <code>Expr</code> on the left hand side of the operator
     * @param secondExpr the <code>Expr</code> on the right hand side of the operator
     */
    public Sub(Expr firstExpr, Expr secondExpr) {
        super(firstExpr, secondExpr);
    }
    
    /**
     * Evaluate <code>this Sub</code>.
     * 
     * @param answerTable an <code>AnswerTable</code> mapping all <code>Ident</code>s
     *                      that might appear in <code>Expr</code>s in
     *                      <code>this Sub</code> to the <code>Value</code> of
     *                      the <code>Question</code> they represent
     * @return a <code>NumericValue</code> representing the result of
     *          subtracting <code>this Sub</code>'s <code>secondExpr</code> from
     *          its <code>firstExpr</code>
     */
    @Override
    public NumericValue eval(AnswerTable answerTable) {
        NumericValue firstValue = NumericValue.cast(getFirstExpr().eval(answerTable));
        NumericValue secondValue = NumericValue.cast(getFirstExpr().eval(answerTable));
        NumericValue secondValueNegative = Neg.negate(secondValue);
        return firstValue.add(secondValueNegative);
    }
}
