package org.uva.sea.ql.ast.question;

import org.uva.sea.ql.ast.Label;
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
     */
    public MoneyQuestion(Ident identifier, Label label) {
        super(identifier, label);
    }
    
}
