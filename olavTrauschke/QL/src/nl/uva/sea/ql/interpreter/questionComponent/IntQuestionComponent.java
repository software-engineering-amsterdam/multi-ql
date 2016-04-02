package nl.uva.sea.ql.interpreter.questionComponent;

import java.beans.PropertyChangeEvent;
import java.text.NumberFormat;
import nl.uva.sea.ql.answerTable.*;
import nl.uva.sea.ql.ast.expr.Expr;
import nl.uva.sea.ql.ast.question.IntQuestion;

/**
 * Objects of this class are <code>FormattedTextFieldComponent</code>s that are
 * used to display <code>IntQuestion</code>s.
 * 
 * @author Olav Trauschke
 * @version 1-apr-2016
 */
public class IntQuestionComponent extends FormattedTextFieldComponent {
    
    /**
     * An <code>int</code> expressing the number of characters that fit in
     * an <code>IntQuestionComponent</code>.
     */
    public static final int NUMBER_OF_COLUMNS = 10;
    
    /**
     * Description of the input format <code>IntQuestionComponent</code>s
     * expect and use, to present to users.
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
     * Set the value of <code>this IntQuestionComponent</code>'s
     * <code>question</code> to the value currently written in it.
     * 
     * @param e an <code>ActionEvent</code> that changes the value of
     *          <code>this IntQuestionComponent</code>
     */
    @Override
    public void setValue(PropertyChangeEvent e) {
        assert e != null;
        Long newValueAsLong = (Long) obtainTextFieldValue();
        IntValue newValue = new IntValue(newValueAsLong);
        setValue(newValue);
    }
    
    /**
     * Make <code>this IntQuestionComponent</code> display a specified
     * <code>Value</code>.
     * 
     * @param newValue a <code>Value this IntQuestionComponent</code> should
     *                  display
     */
    @Override
    public void displayValue(Value newValue) {
        assert newValue instanceof IntValue;
        IntValue intValue = (IntValue) newValue;
        Long valueAsLong = intValue.getValue();
        setTextFieldValue(valueAsLong);
    }
    
}
