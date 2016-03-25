package nl.uva.sea.ql.interpreter;

import java.util.*;
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
 * @version 25-mar-2016
 */
public class DisplayableQuestion implements Observer {
    
    private final Expr displayCondition;
    private final Question question;
    private final boolean isComputedQuestion;
    
    private final Set<Ident> identifiersInDisplayCondition;
    private final Set<Ident> identifiersInCalculation;
    
    private boolean toDisplay;
    private Value value;
    
    /**
     * Constructor for objects of this class.
     * 
     * @param conditionForDisplay an <code>Expr</code> defining when the constructed
     *                              <code>DisplayableQuestion</code> should be
     *                              displayed
     * @param theQuestion a <code>Question</code> that should be displayed when
     *                      <code>conditionToDisplay</code> evaluates to true
     * @param answerTable an <code>AnswerTable</code> mapping all
     *                      <code>Ident</code>s <code>conditionToDisplay</code>
     *                      or <code>theCalculation</code> of <code>theQuestion</code>
     *                      could contain to their current <code>Value</code>s,
     *                      or <code>null</code> when these are unknown
     */
    public DisplayableQuestion(Expr conditionForDisplay, Question theQuestion,
            AnswerTable answerTable) {
        assert conditionForDisplay != null;
        displayCondition = conditionForDisplay;
        question = theQuestion;
        isComputedQuestion = theQuestion.isComputed();
        
        IdentCollector displayConditionidentifierCollector = new IdentCollector();
        displayCondition.accept(displayConditionidentifierCollector);
        identifiersInDisplayCondition = displayConditionidentifierCollector.getIdentifiers();
        
        IdentCollector calculationIdentifierCollector = new IdentCollector();
        question.calculationAccept(calculationIdentifierCollector);
        identifiersInCalculation = calculationIdentifierCollector.getIdentifiers();
        
        toDisplay = ((BooleanValue) displayCondition.eval(answerTable)).getValue();
        value = isComputedQuestion ? question.evalCalculation(answerTable) : null;
    }
    
    /**
     * Update the status of <code>this DisplayableQuestion</code> - i.e. whether
     * it is <code>toDisplay</code> and its <code>value</code> to correspond to
     * a new, or - more likely - updated, <code>AnswerTable</code>. Note that
     * this method should be called for each change to the
     * <code>AnswerTable</code> and cannot be executed only once for multiple
     * changes.
     * 
     * @param observable an <code>Observable</code> that should be an
     *                      <code>AnswerTable</code>. More specifically, the
     *                      new or updated
     *                      <code>AnswerTable this DisplayableQuestion</code>'s
     *                      status should comply to
     * @param argument an <code>Ident</code> indicating which <code>Value</code>
     *                  in <code>observable</code> has changed as compared to
     *                  the <code>SymbolTable</code> that was provided on the
     *                  last <code>update</code> or at construction if this is
     *                  the first call to <code>update</code> on
     *                  <code>this DisplayableQuestion</code>
     */
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
            value = question.evalCalculation(answerTable); 
        }
    }
    
}
