package org.uva.sea.ql.ast.question;

import org.uva.sea.ql.ast.Label;
import org.uva.sea.ql.ast.expr.Ident;

/**
 * Representation of <code>Question</code>s that return dates in an AST.
 * 
 * @author Olav Trauschke
 * @version 25-feb-2016
 */
public class DateQuestion extends Question {
    
    /**
     * Constructor for objects of this class.
     * 
     * @param identifier the <code>Ident</code> used to identify the constructed
     *                      <code>DateQuestion</code>
     * @param label the <code>Label</code> to display with the constructed
     *              <code>DateQuestion</code>
     */
    public DateQuestion(Ident identifier, Label label) {
        super(identifier, label);
    }
    
}
