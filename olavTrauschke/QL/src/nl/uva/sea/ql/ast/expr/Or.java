package nl.uva.sea.ql.ast.expr;

import nl.uva.sea.ql.answerTable.AnswerTable;
import nl.uva.sea.ql.answerTable.BooleanValue;

/**
 * Representation of boolean disjunction in an AST.
 * 
 * @author Olav Trauschke
 * @version 16-mar-2016
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
    
    /**
     * Evaluate <code>this Or</code>.
     * 
     * @param answerTable an <code>AnswerTable</code> mapping all <code>Ident</code>s
     *                      that might appear in <code>Expr</code>s in
     *                      <code>this Or</code> to the <code>Value</code> of
     *                      the <code>Question</code> they represent
     * @return a <code>BooleanValue</code> representing the result of disjuncting
     *          the value of <code>this And</code>'s <code>firstExpr</code> to
     *          its <code>secondExpr</code>
     */
    @Override
    public BooleanValue eval(AnswerTable answerTable) {
        BooleanValue firstValue = (BooleanValue) getFirstExpr().eval(answerTable);
        BooleanValue secondValue = (BooleanValue) getSecondExpr().eval(answerTable);
        return firstValue.disjunct(secondValue);
    }
    
}
