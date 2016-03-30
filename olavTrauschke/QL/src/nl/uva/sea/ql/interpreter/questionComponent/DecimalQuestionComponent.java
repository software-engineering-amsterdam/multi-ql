package nl.uva.sea.ql.interpreter.questionComponent;

import java.beans.PropertyChangeEvent;
import java.text.NumberFormat;
import nl.uva.sea.ql.answerTable.*;
import nl.uva.sea.ql.ast.expr.Expr;
import nl.uva.sea.ql.ast.question.DecimalQuestion;

/**
 * TODO document
 * 
 * @author Olav Trauschke
 * @version 30-mar-2016
 */
public class DecimalQuestionComponent extends FormattedTextFieldComponent {
    
    /**
     * TODO document
     */
    public static final int NUMBER_OF_COLUMNS = 10;
    
    /**
     * TODO document
     */
    public static final String FORMAT_DESCRIPTION = "number";
    
    /**
     * TODO document
     * 
     * @param conditionForDisplay
     * @param theQuestion
     * @param theAnswerTable 
     */
    public DecimalQuestionComponent(Expr conditionForDisplay,
            DecimalQuestion theQuestion, AnswerTable theAnswerTable) {
        super(conditionForDisplay, theQuestion, theAnswerTable,
                NumberFormat.getInstance(), NUMBER_OF_COLUMNS, FORMAT_DESCRIPTION);
    }
    
    /**
     * TODO document
     * 
     * @param e 
     */
    @Override
    public void setValue(PropertyChangeEvent e) {
        assert e != null;
        Object newValueAsObject = getTextFieldValue();
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
     * TODO document
     * 
     * @param newValue 
     */
    @Override
    public void displayValue(Value newValue) {
        assert newValue instanceof DecimalValue;
        DecimalValue decimalValue = (DecimalValue) newValue;
        Double valueAsDouble = decimalValue.getValue();
        setTextFieldValue(valueAsDouble);
    }
    
}
