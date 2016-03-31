package nl.uva.sea.ql.interpreter.questionComponent;

import java.beans.PropertyChangeEvent;
import java.text.NumberFormat;
import nl.uva.sea.ql.answerTable.*;
import nl.uva.sea.ql.ast.expr.Expr;
import nl.uva.sea.ql.ast.question.DecimalQuestion;

/**
 * Objects of this class are <code>FormattedTextFieldComponent</code>s that are
 * used to display <code>DecimalQuestion</code>s.
 * 
 * @author Olav Trauschke
 * @version 1-apr-2016
 */
public class DecimalQuestionComponent extends FormattedTextFieldComponent {
    
    /**
     * An <code>int</code> expressing the number of characters that fit in
     * a <code>DecimalQuestionComponent</code>.
     */
    public static final int NUMBER_OF_COLUMNS = 10;
    
    /**
     * Description of the input format <code>DecimalQuestionComponent</code>s
     * expect and use, to present to users.
     */
    public static final String FORMAT_DESCRIPTION = "number";
    
    /**
     * Constructor for objects of this class.
     * 
     * @param conditionForDisplay an <code>Expr</code> defining when the constructed
     *                              <code>DecimalQuestionComponent</code>
     *                              should be displayed
     * @param theQuestion a <code>DecimalQuestion</code> that should be displayed
     *                      when <code>conditionToDisplay</code> evaluates to
     *                      <code>true</code>
     * @param theAnswerTable an <code>AnswerTable</code> mapping all
     *                      <code>Ident</code>s <code>conditionToDisplay</code>
     *                      or <code>theCalculation</code> of <code>theQuestion</code>
     *                      could contain to their current <code>Value</code>s,
     *                      or <code>null</code> when these are unknown
     */
    public DecimalQuestionComponent(Expr conditionForDisplay,
            DecimalQuestion theQuestion, AnswerTable theAnswerTable) {
        super(conditionForDisplay, theQuestion, theAnswerTable,
                NumberFormat.getInstance(), NUMBER_OF_COLUMNS, FORMAT_DESCRIPTION);
    }
    
    /**
     * Set the value of <code>this DateQuestionComponent</code>'s
     * <code>question</code> to the value currently written in it.
     * 
     * @param e an <code>ActionEvent</code> that changes the value of
     *          <code>this DecimalQuestionComponent</code>
     */
    @Override
    public void setValue(PropertyChangeEvent e) {
        assert e != null;
        Object newValueAsObject = obtainTextFieldValue();
        Double newValueAsDouble;
        try {
            newValueAsDouble = (Double) newValueAsObject;
        }
        catch (ClassCastException cce) {
            //When a whole number was entered, a Long is returned instead of
            //a Double, so we create a Double out of it first
            Long newValueAsLong = (Long) newValueAsObject;
            newValueAsDouble = newValueAsLong.doubleValue();
        }
        DecimalValue newValue = new DecimalValue(newValueAsDouble);
        setValue(newValue);
    }
    
    /**
     * Make <code>this DecimalQuestionComponent</code> display a specified
     * <code>Value</code>.
     * 
     * @param newValue a <code>Value this DecimalQuestionComponent</code> should
     *                  display
     */
    @Override
    public void displayValue(Value newValue) {
        assert newValue instanceof DecimalValue;
        DecimalValue decimalValue = (DecimalValue) newValue;
        Double valueAsDouble = decimalValue.getValue();
        setTextFieldValue(valueAsDouble);
    }
    
}
