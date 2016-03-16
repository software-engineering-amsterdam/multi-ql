package nl.uva.sea.ql.ast.expr;

import nl.uva.sea.ql.answerTable.*;

/**
 * Representation of inverse equality comparisons in an AST.
 * 
 * @author Olav Trauschke
 * @version 16-mar-2016
 */
public class NEq extends ComparisonExpr {
    
    /**
     * Constructor for objects of class <code>NEq</code>.
     * 
     * @param firstExpr the <code>Expr</code> on the left hand side of the operator
     * @param secondExpr the <code>Expr</code> on the right hand side of the operator
     */
    public NEq(Expr firstExpr, Expr secondExpr) {
        super(firstExpr, secondExpr);
    }
    
    /**
     * Evaluate <code>this NEq</code>.
     * 
     * @param answerTable an <code>AnswerTable</code> mapping all <code>Ident</code>s
     *                      that might appear in <code>Expr</code>s in
     *                      <code>this NEq</code> to the <code>Value</code> of
     *                      the <code>Question</code> they represent
     * @return a <code>BooleanValue</code> representing the negation of the
     *          result of comparing the value of <code>this NEq</code>'s
     *          <code>firstExpr</code> to its <code>secondExpr</code>
     */
    @Override
    public BooleanValue eval(AnswerTable answerTable) {
        Value firstValue = getFirstExpr().eval(answerTable);
        Value secondValue = getSecondExpr().eval(answerTable);
        return firstValue.ternaryEquals(secondValue);
    }
    
}
