package nl.uva.sea.ql.interpreter;

import nl.uva.sea.ql.ast.expr.Expr;
import nl.uva.sea.ql.ast.question.Question;

/**
 * Objects of this class represent
 * {@link nl.uva.sea.ql.ast.question.Question Question}s together with the
 * conditions under which they should be displayed.
 * 
 * @author Olav Trauschke
 * @version 24-mar-2016
 */
public class DisplayableQuestion {
    
    private final Expr isToDisplay;
    private final Question question;
    
    /**
     * Constructor for objects of this class.
     * 
     * @param conditionToDisplay an <code>Expr</code> defining when the constructed
     *                              <code>DisplayableQuestion</code> should be
     *                              displayed
     * @param theQuestion a <code>Question</code> that should be displayed when
     *                      <code>conditionToDisplay</code> evaluates to true
     */
    public DisplayableQuestion(Expr conditionToDisplay, Question theQuestion) {
        isToDisplay = conditionToDisplay;
        question = theQuestion;
    }
    
}
