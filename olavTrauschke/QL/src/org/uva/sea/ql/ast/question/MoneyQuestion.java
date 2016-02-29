package org.uva.sea.ql.ast.question;

import org.uva.sea.ql.ast.Label;
import org.uva.sea.ql.ast.expr.Expr;
import org.uva.sea.ql.ast.expr.Ident;

/**
 * Representation of <code>Question</code>s that return money in an AST.
 * 
 * @author Olav Trauschke
 * @version 25-feb-2016
 */
public class MoneyQuestion extends Question {
    
    /**
     * Constructor for objects of this class.
     * 
     * @param identifier the <code>Ident</code> used to identify the constructed
     *                      <code>MoneyQuestion</code>
     * @param label the <code>Label</code> to display with the constructed
     *              <code>MoneyQuestion</code>
     * @param calculation an <code>Expr</code> defining how to compute the value
     *                      of the constructed <code>MoneyQuestion</code> or
     *                      <code>null</code> if it should be answered by the user
     */
    public MoneyQuestion(Ident identifier, Label label, Expr calculation) {
        super(identifier, label, calculation);
    }
    
}
