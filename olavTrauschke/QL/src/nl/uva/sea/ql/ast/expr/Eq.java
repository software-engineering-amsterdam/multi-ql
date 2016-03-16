package nl.uva.sea.ql.ast.expr;

import nl.uva.sea.ql.answerTable.*;

/**
 * Representation of equality comparisons in an AST.
 * 
 * @author Olav Trauschke
 * @version 16-mrt-2016
 */
public class Eq extends ComparisonExpr {
    
    /**
     * Constructor for objects of class <code>Eq</code>.
     * 
     * @param firstExpr the <code>Expr</code> on the left hand side of the operator
     * @param secondExpr the <code>Expr</code> on the right hand side of the operator
     */
    public Eq(Expr firstExpr, Expr secondExpr) {
        super(firstExpr, secondExpr);
    }
    
    /**
     * Evaluate <code>this Eq</code>.
     * 
     * @param answerTable an <code>AnswerTable</code> mapping all <code>Ident</code>s
     *                      that might appear in <code>Expr</code>s in
     *                      <code>this Eq</code> to the <code>Value</code> of
     *                      the <code>Question</code> they represent
     * @return a <code>BooleanValue</code> representing the result of comparing
     *          the value of <code>this Eq</code>'s <code>firstExpr</code> to
     *          its <code>secondExpr</code>
     */
    @Override
    public BooleanValue eval(AnswerTable answerTable) {
        Value firstValue = getFirstExpr().eval(answerTable);
        Value secondValue = getSecondExpr().eval(answerTable);
        return firstValue.ternaryEquals(secondValue);
    }
    
}
