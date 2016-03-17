package nl.uva.sea.ql.ast.expr;

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
    
    @Override
    public NumericValue eval(AnswerTable answerTable) {
        NumericValue firstValue = (NumericValue) getFirstExpr().eval(answerTable);
        NumericValue secondValue = (NumericValue) getSecondExpr().eval(answerTable);
        return firstValue.divide(secondValue);
    }
}
