package nl.uva.sea.ql.interpreter.questionComponent;

import java.beans.PropertyChangeEvent;
import java.text.NumberFormat;
import nl.uva.sea.ql.answerTable.*;
import nl.uva.sea.ql.ast.expr.Expr;
import nl.uva.sea.ql.ast.question.IntQuestion;

/**
 * TODO document
 * 
 * @author Olav Trauschke
 * @version 30-mrt-2016
 */
public class IntQuestionComponent extends FormattedTextFieldComponent {
    
    /**
     * TODO document
     */
    public static final int NUMBER_OF_COLUMNS = 10;
    
    /**
     * TODO document
     */
    public static final String FORMAT_DESCRIPTION = "whole number";
    
    /**
     * Constructor for objects of this class.
     * 
     * @param conditionForDisplay an <code>Expr</code> defining when the constructed
     *                              <code>IntQuestionComponent</code>
     *                              should be displayed
     * @param theQuestion an <code>IntQuestion</code> that should be displayed
     *                      when <code>conditionToDisplay</code> evaluates to
     *                      <code>true</code>
     * @param theAnswerTable an <code>AnswerTable</code> mapping all
     *                      <code>Ident</code>s <code>conditionToDisplay</code>
     *                      or <code>theCalculation</code> of <code>theQuestion</code>
     *                      could contain to their current <code>Value</code>s,
     *                      or <code>null</code> when these are unknown
     */
    public IntQuestionComponent(Expr conditionForDisplay,
            IntQuestion theQuestion, AnswerTable theAnswerTable) {
        super(conditionForDisplay, theQuestion, theAnswerTable,
                NumberFormat.getIntegerInstance(), NUMBER_OF_COLUMNS,
                FORMAT_DESCRIPTION);
    }
    
    /**
     * TODO document
     * 
     * @param e 
     */
    @Override
    public void setValue(PropertyChangeEvent e) {
        assert e != null;
        Long newValueAsLong = (Long) getTextFieldValue();
        IntValue newValue = new IntValue(newValueAsLong);
        setValue(newValue);
    }
    
    /**
     * TODO document
     * 
     * @param newValue 
     */
    @Override
    public void displayValue(Value newValue) {
        assert newValue instanceof IntValue;
        IntValue intValue = (IntValue) newValue;
        Long valueAsLong = intValue.getValue();
        setTextFieldValue(valueAsLong);
    }
    
}
