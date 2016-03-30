package nl.uva.sea.ql.interpreter.questionComponent;

import nl.uva.sea.ql.interpreter.questionComponent.QuestionComponentListener;
import nl.uva.sea.ql.interpreter.questionComponent.QuestionComponentChangeEvent;
import java.util.*;
import javax.swing.JComponent;
import nl.uva.sea.ql.answerTable.*;
import nl.uva.sea.ql.ast.expr.Expr;
import nl.uva.sea.ql.ast.expr.Ident;
import nl.uva.sea.ql.ast.question.Question;
import nl.uva.sea.ql.generalPurposeVisitors.IdentCollector;

/**
 * Objects of this class represent
 * {@link nl.uva.sea.ql.ast.question.Question Question}s together with the
 * conditions under which they should be displayed.
 * 
 * @author Olav Trauschke
 * @version 29-mar-2016
 */
public class BasicQuestionComponent extends JComponent
        implements QuestionComponent {
    
    private final Expr displayCondition;
    private final Question question;
    private final boolean isComputedQuestion;
    
    private final Set<Ident> identifiersInDisplayCondition;
    private final Set<Ident> identifiersInCalculation;
    
    private final AnswerTable answerTable;
    
    private final List<QuestionComponentListener> listeners;
    
    private final ConcreteQuestionComponent concreteQuestion;
    
    private boolean isToDisplay;
    private Value value;
    
    /**
     * Constructor for objects of this class.
     * 
     * @param conditionForDisplay an <code>Expr</code> defining when the constructed
     *                              <code>QuestionComponent</code> should be
     *                              displayed
     * @param theQuestion a <code>Question</code> that should be displayed when
     *                      <code>conditionToDisplay</code> evaluates to
     *                      <code>true</code>
     * @param theAnswerTable an <code>AnswerTable</code> mapping all
     *                      <code>Ident</code>s <code>conditionToDisplay</code>
     *                      or <code>theCalculation</code> of <code>theQuestion</code>
     *                      could contain to their current <code>Value</code>s,
     *                      or <code>null</code> when these are unknown
     * @param theConcreteQuestion a <code>QuestionComponent</code> that is not
     *                              a <code>BasicQuestionComponent</code> but
     *                              represents the same <code>Question</code> as
     *                              the constructed
     *                              <code>BasicQuestionComponent</code>,
     *                              the constructed
     *                              <code>BasicQuestionComponent</code> must
     *                              become <code>theConcreteQuestion</code>'s
     *                              <code>BasicQuestion</code> for concistency
     */
    BasicQuestionComponent(Expr conditionForDisplay, Question theQuestion,
            AnswerTable theAnswerTable, ConcreteQuestionComponent theConcreteQuestion) {
        assert conditionForDisplay != null;
        assert theQuestion != null;
        assert theAnswerTable != null;
        assert theConcreteQuestion != null;
        
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
        
        BooleanValue isToDisplayValue = BooleanValue.cast(displayCondition.eval(theAnswerTable));
        Boolean isToDisplayBoolean = isToDisplayValue.getValue();
        isToDisplay = isToDisplayBoolean == null ? false : isToDisplayBoolean;
        value = isComputedQuestion ? question.evalCalculation(theAnswerTable) : null;
        
        concreteQuestion = theConcreteQuestion;
    }
    
    /**
     * Update the status of <code>this BasicQuestionComponent</code> - i.e.
     * whether it is <code>toDisplay</code> and its <code>value</code> to
     * reflect a change in <code>theAnswerTable</code>. Note that this method
     * should be called for each change to the <code>AnswerTable</code> and
     * cannot be executed only once for multiple changes.
     * 
     * @param observable the <code>Observable</code> that was changed so that
     *                      this method was called, unused
     * @param argument an <code>Ident</code> indicating which <code>Value</code>
     *                  in <code>theAnswerTable</code> was changed
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
            setValue(question.evalCalculation(answerTable), true); 
        }
    }
    
    /**
     * @return whether or not <code>this BasicQuestionComponent</code> should
     *          currently be displayed
     */
    @Override
    public boolean isToDisplay() {
        return isToDisplay;
    }
    
    /**
     * Have a specified <code>DisplayableQeustionListener</code> be notified
     * when <code>this BasicQuestionComponent</code> changes. 
     * 
     * @param listener a <code>QuestionComponentListener</code> that needs to
     *                  know when <code>this BasicQuestionComponent</code>
     *                  changes
     */
    @Override
    public void addListener(QuestionComponentListener listener) {
        assert listener != null;
        listeners.add(listener);
    }
    
    /**
     * @return the <code>Ident</code> that is <code>theIdentifier</code> of
     *          <code>theQuestion this BasicQuestionComponent</code> represents
     */
    @Override
    public Ident getIdentifier() {
        return question.getIdentifier();
    }
    
    /**
     * Evaluate <code>theCalculation</code> of
     * <code>theQuestion this BasicQuestionComponent</code> represents.
     * 
     * @return a <code>Value</code> representing the current value of
     *          <code>theCalculation</code> of <code>theQuestion</code> of
     *          <code>this BasicQuestionComponent</code>
     */
    @Override
    public Value evalCalculation() {
        return question.evalCalculation(answerTable);
    }
    
    /**
     * @return the <code>QuestionComponent</code> that was passed to the
     *          constructor as <code>theConcreteQuestion</code> that set
     *          <code>this BasicQuestionComponent</code> as its
     *          <code>basicQuestion</code> when
     *          <code>this BasicQuestionComponent</code> was constructed
     */
    public QuestionComponent getConcreteQuestion() {
        return concreteQuestion;
    }
    
    /**
     * Set the <code>value</code> of <code>this BasicQuestionComponent</code> and
     * update its <code>answerTable</code> if and only if <code>newValue</code>
     * is not equal to <code>value</code>.
     * 
     * @param newValue the <code>Value</code> to set as the <code>value</code>
     *                  of <code>this BasicQuestionComponent</code>. The type of
     *                  this <code>Value</code> must match the type of
     *                  <code>this BasicQuestionComponent</code>'s
     *                  <code>question</code> and in case this is a computed
     *                  question (i.e. its <code>calculation != null</code>) it
     *                  must be the <code>Value</code> this <code>Expr</code>
     *                  currently evaluates to
     * @param changeDisplay a <code>boolean</code> telling whether the display
     *                      still needs to be changed (or a display change was
     *                      the reason for this call)
     */
    void setValue(Value newValue, boolean changeDisplay) {
        if (!newValue.equals(value)) {
            value = newValue;
            answerTable.update(question.getIdentifier(), newValue);
            if (changeDisplay) {
                concreteQuestion.displayValue(value);
            }
            notifyListeners(false);
        }
    }
    
    /**
     * Notify all <code>QuestionComponentListener</code>s that were added to
     * <code>this BasicQuestionComponent</code> that
     * <code>this BasicQuestionComponent</code> has changed by calling their
     * {@link nl.uva.sea.ql.interpreter.listener.QuestionComponentListener#questionChanged(nl.uva.sea.ql.interpreter.listener.QuestionComponentChangeEvent)
     * questionChanged(QuestionComponentChangeEvent)} methods.
     * 
     * @param toDisplayChanged a <code>boolean</code> telling whether or not
     *                          whether <code>this BasicQuestionComponent</code>
     *                          should be displayed changed
     */
    void notifyListeners(boolean toDisplayChanged) {
        QuestionComponentChangeEvent event
                = new QuestionComponentChangeEvent(this, toDisplayChanged);
        listeners.forEach((QuestionComponentListener listener) -> listener.questionChanged(event));
    }
    
}
