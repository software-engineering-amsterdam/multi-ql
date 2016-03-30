package nl.uva.sea.ql.ast.expr;

import nl.uva.sea.ql.answerTable.AnswerTable;
import nl.uva.sea.ql.answerTable.NumericValue;

/**
 * Representation of multiplication in an AST.
 * 
 * @author Olav Trauschke
 * @version 26-mar-2016
 */
public class Mul extends BinaryNumericOperatorExpr {
    
    /**
     * Constructor for objects of class <code>Mul</code>.
     * 
     * @param firstExpr the <code>Expr</code> on the left hand side of the operator
     * @param secondExpr the <code>Expr</code> on the right hand side of the operator
     */
    public Mul(Expr firstExpr, Expr secondExpr) {
        super(firstExpr, secondExpr);
    }
    
    /**
     * Evaluate <code>this Mul</code>.
     * 
     * @param answerTable an <code>AnswerTable</code> mapping all <code>Ident</code>s
     *                      that might appear in <code>Expr</code>s in
     *                      <code>this Mul</code> to the <code>Value</code> of
     *                      the <code>Question</code> they represent
     * @return a <code>NumericValue</code> representing the result of multiplying
     *          the <code>firstExpr</code> of <code>this Mul</code> by its
     *          <code>secondFactor</code>
     */
    @Override
    public NumericValue eval(AnswerTable answerTable) {
        NumericValue firstValue = NumericValue.cast(getFirstExpr().eval(answerTable));
        NumericValue secondValue = NumericValue.cast(getSecondExpr().eval(answerTable));
        return firstValue.multiply(secondValue);
    }
}
