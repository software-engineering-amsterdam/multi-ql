package nl.uva.sea.ql.ast.expr;

import nl.uva.sea.ql.answerTable.*;

/**
 * Representation of "less than" comparisons in an AST.
 * 
 * @author Olav Trauschke
 * @version 16-mar-2016
 */
public class LT extends OrderedComparisonExpr {
    
    /**
     * Constructor for objects of class <code>LT</code>.
     * 
     * @param firstExpr the <code>Expr</code> on the left hand side of the operator
     * @param secondExpr the <code>Expr</code> on the right hand side of the operator
     */
    public LT(Expr firstExpr, Expr secondExpr) {
        super(firstExpr, secondExpr);
    }
    
    /**
     * Evaluate <code>this LT</code>.
     * 
     * @param answerTable an <code>AnswerTable</code> mapping all <code>Ident</code>s
     *                      that might appear in <code>Expr</code>s in
     *                      <code>this LT</code> to the <code>Value</code> of
     *                      the <code>Question</code> they represent
     * @return a <code>BooleanValue</code> representing the result of checking
     *          whether <code>this LT</code>'s <code>firstExpr</code> is less
     *          than its <code>secondExpr</code>
     */
    @Override
    public BooleanValue eval(AnswerTable answerTable) {
        NumericValue firstValue = (NumericValue) getFirstExpr().eval(answerTable);
        NumericValue secondValue = (NumericValue) getSecondExpr().eval(answerTable);
        BooleanValue equalValues = firstValue.ternaryEquals(secondValue);
        BooleanValue greaterThan = firstValue.ternaryGreaterThan(secondValue);
        BooleanValue gEq = equalValues.disjunct(greaterThan);
        return gEq.negate();
    }
    
}
