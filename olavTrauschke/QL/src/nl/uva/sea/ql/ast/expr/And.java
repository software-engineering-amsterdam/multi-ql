package nl.uva.sea.ql.ast.expr;

import nl.uva.sea.ql.answerTable.AnswerTable;
import nl.uva.sea.ql.answerTable.BooleanValue;

/**
 * Representation of boolean conjunction in an AST.
 * 
 * @author Olav Trauschke
 * @version 16-mrt-2016
 */
public class And extends BooleanConjunctiveExpr {
    
    /**
     * Constructor for objects of class <code>And</code>.
     * 
     * @param firstExpr the <code>Expr</code> on the left hand side of the conjunction
     * @param secondExpr the <code>Expr</code> on the right hand side of the conjunction
     */
    public And(Expr firstExpr, Expr secondExpr) {
        super(firstExpr, secondExpr);
    }
    
    /**
     * Evaluate <code>this And</code>.
     * 
     * @param answerTable an <code>AnswerTable</code> mapping all <code>Ident</code>s
     *                      that might appear in <code>Expr</code>s in
     *                      <code>this And</code> to the <code>Value</code> of
     *                      the <code>Question</code> they represent
     * @return a <code>BooleanValue</code> representing the result of conjuncting
     *          the value of <code>this And</code>'s <code>firstExpr</code> to
     *          its <code>secondExpr</code>
     */
    @Override
    public BooleanValue eval(AnswerTable answerTable) {
        BooleanValue firstValue = (BooleanValue) getFirstExpr().eval(answerTable);
        BooleanValue secondValue = (BooleanValue) getSecondExpr().eval(answerTable);
        return firstValue.conjunct(secondValue);
    }
    
}
