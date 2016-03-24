package nl.uva.sea.ql.interpreter;

import java.util.Observable;
import java.util.Observer;
import nl.uva.sea.ql.answerTable.*;
import nl.uva.sea.ql.ast.expr.Expr;
import nl.uva.sea.ql.ast.expr.Ident;
import nl.uva.sea.ql.ast.question.Question;

/**
 * Objects of this class represent
 * {@link nl.uva.sea.ql.ast.question.Question Question}s together with the
 * conditions under which they should be displayed.
 * 
 * @author Olav Trauschke
 * @version 24-mar-2016
 */
public class DisplayableQuestion implements Observer {
    
    private final Expr displayCondition;
    private final Question question;
    private final boolean isComputedQuestion;
    private boolean toDisplay;
    private Value value;
    
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
        displayCondition = conditionToDisplay;
        question = theQuestion;
        isComputedQuestion = theQuestion.isComputed();
        //TODO set toDisplay
        value = isComputedQuestion ? theQuestion.evalCalculation() : null;
    }
    
    //TODO document
    @Override
    public void update(Observable observable, Object argument) {
        assert observable instanceof AnswerTable;
        assert argument instanceof Ident;
        
        AnswerTable answerTable = (AnswerTable) observable;
        Ident identifier = (Ident) argument;
        
        if (identifiersInDisplayCondition.contains(identifier)) {
            BooleanValue toDisplayValue = (BooleanValue) displayCondition.eval(answerTable);
            toDisplay = toDisplayValue == null ? false : toDisplayValue.getValue();
        }
        
        if (isComputedQuestion && identifiersInCalculation.contains(identifier)) {
            value = question.evalCalculation(); 
        }
    }
    
}
