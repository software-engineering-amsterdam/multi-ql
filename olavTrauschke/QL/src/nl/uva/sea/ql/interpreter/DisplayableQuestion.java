package nl.uva.sea.ql.interpreter;

import java.util.*;
import nl.uva.sea.ql.answerTable.*;
import nl.uva.sea.ql.ast.expr.Expr;
import nl.uva.sea.ql.ast.expr.Ident;
import nl.uva.sea.ql.ast.question.Question;
import nl.uva.sea.ql.generalPurposeVisitors.IdentCollector;
import nl.uva.sea.ql.interpreter.listener.*;

/**
 * Objects of this class represent
 * {@link nl.uva.sea.ql.ast.question.Question Question}s together with the
 * conditions under which they should be displayed.
 * 
 * @author Olav Trauschke
 * @version 26-mar-2016
 */
public class DisplayableQuestion implements Observer {
    
    private final Expr displayCondition;
    private final Question question;
    private final boolean isComputedQuestion;
    
    private final Set<Ident> identifiersInDisplayCondition;
    private final Set<Ident> identifiersInCalculation;
    
    private final AnswerTable answerTable;
    
    private final List<DisplayableQuestionListener> listeners;
    
    private boolean isToDisplay;
    private Value value;
    
    /**
     * Constructor for objects of this class.
     * 
     * @param conditionForDisplay an <code>Expr</code> defining when the constructed
     *                              <code>DisplayableQuestion</code> should be
     *                              displayed
     * @param theQuestion a <code>Question</code> that should be displayed when
     *                      <code>conditionToDisplay</code> evaluates to true
     * @param theAnswerTable an <code>AnswerTable</code> mapping all
     *                      <code>Ident</code>s <code>conditionToDisplay</code>
     *                      or <code>theCalculation</code> of <code>theQuestion</code>
     *                      could contain to their current <code>Value</code>s,
     *                      or <code>null</code> when these are unknown
     */
    public DisplayableQuestion(Expr conditionForDisplay, Question theQuestion,
            AnswerTable theAnswerTable) {
        assert conditionForDisplay != null;
        assert theQuestion != null;
        assert theAnswerTable != null;
        
        displayCondition = conditionForDisplay;
        question = theQuestion;
        isComputedQuestion = theQuestion.isComputed();
        answerTable = theAnswerTable;
        
        IdentCollector displayConditionidentifierCollector = new IdentCollector();
        displayCondition.accept(displayConditionidentifierCollector);
        identifiersInDisplayCondition = displayConditionidentifierCollector.getIdentifiers();
        
        IdentCollector calculationIdentifierCollector = new IdentCollector();
        question.calculationAccept(calculationIdentifierCollector);
        identifiersInCalculation = calculationIdentifierCollector.getIdentifiers();
        
        listeners = new ArrayList<>();
        
        isToDisplay = ((BooleanValue) displayCondition.eval(theAnswerTable)).getValue();
        value = isComputedQuestion ? question.evalCalculation(theAnswerTable) : null;
    }
    
    /**
     * Update the status of <code>this DisplayableQuestion</code> - i.e. whether
     * it is <code>toDisplay</code> and its <code>value</code> to correspond to
     * a new, or - more likely - updated, <code>AnswerTable</code>. Note that
     * this method should be called for each change to the
     * <code>AnswerTable</code> and cannot be executed only once for multiple
     * changes.
     * 
     * @param observable the <code>Observable</code> that was changed so that
     *                      this method was called, unused
     * @param argument an <code>Ident</code> indicating which <code>Value</code>
     *                  in <code>answerTable</code> was changed
     */
    @Override
    public void update(Observable observable, Object argument) {
        assert argument instanceof Ident;
        
        Ident identifier = (Ident) argument;
        
        if (identifiersInDisplayCondition.contains(identifier)) {
            BooleanValue toDisplayValue = (BooleanValue) displayCondition.eval(answerTable);
            boolean wasToDisplay = isToDisplay;
            isToDisplay = toDisplayValue == null ? false : toDisplayValue.getValue();
            if (isToDisplay != wasToDisplay) {
                notifyListeners(true);
            }
        }
        
        if (isComputedQuestion && identifiersInCalculation.contains(identifier)) {
            setValue(question.evalCalculation(answerTable)); 
        }
    }
    
    /**
     * TODO document
     * 
     * @return 
     */
    public boolean isToDisplay() {
        return isToDisplay;
    }
    
    /**
     * TODO document
     * 
     * @param listener 
     */
    public void addListener(DisplayableQuestionListener listener) {
        listeners.add(listener);
    }
    
    /**
     * TODO document
     */
    private void notifyListeners(boolean toDisplayChanged) {
        DisplayableQuestionChangeEvent event
                = new DisplayableQuestionChangeEvent(this, toDisplayChanged);
        listeners.forEach((DisplayableQuestionListener listener) -> listener.questionChanged(event));
    }
    
    /**
     * Set the <code>value</code> of <code>this DisplayableQuestion</code> and
     * update its <code>answerTable</code> if and only if <code>newValue</code>
     * is not equal to <code>value</code>.
     * 
     * @param newValue the <code>Value</code> to set as the <code>value</code>
     *                  of <code>this DisplayableQuestion</code>. The type of
     *                  this <code>Value</code> must match the type of
     *                  <code>this DisplayableQuestion</code>'s
     *                  <code>question</code> and in case this is a computed
     *                  question (i.e. its <code>calculation != null</code>) it
     *                  must be the <code>Value</code> this <code>Expr</code>
     *                  currently evaluates to
     */
    private void setValue(Value newValue) {
        if (!newValue.equals(value)) {
            value = newValue;
            answerTable.update(question.getIdentifier(), newValue);
            notifyListeners(false);
        }
    }
    
}
