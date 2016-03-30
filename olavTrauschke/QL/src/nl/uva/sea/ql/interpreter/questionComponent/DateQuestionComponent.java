package nl.uva.sea.ql.interpreter.questionComponent;

import java.beans.PropertyChangeEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import nl.uva.sea.ql.answerTable.*;
import nl.uva.sea.ql.ast.expr.Expr;
import nl.uva.sea.ql.ast.question.DateQuestion;

/**
 * TODO document
 * 
 * @author Olav Trauschke
 * @version 30-mar-2016
 */
public class DateQuestionComponent extends FormattedTextFieldComponent {
    
    /**
     * TODO document
     */
    public static final int NUMBER_OF_COLUMNS = 10;
    
    /**
     * TODO document
     */
    public static final String DATE_FORMAT = "d-M-yyyy";
    
    /**
     * Constructor for objects of this class.
     * 
     * @param conditionForDisplay an <code>Expr</code> defining when the constructed
     *                              <code>DateQuestionComponent</code>
     *                              should be displayed
     * @param theQuestion a <code>DateQuestion</code> that should be displayed
     *                      when <code>conditionToDisplay</code> evaluates to
     *                      <code>true</code>
     * @param theAnswerTable an <code>AnswerTable</code> mapping all
     *                      <code>Ident</code>s <code>conditionToDisplay</code>
     *                      or <code>theCalculation</code> of <code>theQuestion</code>
     *                      could contain to their current <code>Value</code>s,
     *                      or <code>null</code> when these are unknown
     */
    public DateQuestionComponent(Expr conditionForDisplay,
            DateQuestion theQuestion, AnswerTable theAnswerTable) {
        super(conditionForDisplay, theQuestion, theAnswerTable,
                new SimpleDateFormat(DATE_FORMAT), NUMBER_OF_COLUMNS,
                DATE_FORMAT.toLowerCase());
    }
    
    /**
     * TODO document
     * 
     * @param e 
     */
    @Override
    public void setValue(PropertyChangeEvent e) {
        assert e != null;
        Date date = (Date) getTextFieldValue();
        DateValue newValue = new DateValue(date);
        setValue(newValue);
    }
    
    /**
     * TODO document
     * 
     * @param newValue 
     */
    @Override
    public void displayValue(Value newValue) {
        assert newValue instanceof DateValue;
        DateValue dateValue = (DateValue) newValue;
        Date valueAsDate = dateValue.getValue();
        setTextFieldValue(valueAsDate);
    }
    
}
