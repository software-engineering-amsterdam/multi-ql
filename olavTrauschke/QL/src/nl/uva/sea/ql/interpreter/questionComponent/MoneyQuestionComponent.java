package nl.uva.sea.ql.interpreter.questionComponent;

import java.beans.PropertyChangeEvent;
import java.math.BigDecimal;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import nl.uva.sea.ql.answerTable.*;
import nl.uva.sea.ql.ast.expr.Expr;
import nl.uva.sea.ql.ast.question.MoneyQuestion;

/**
 * TODO document
 * 
 * @author Olav Trauschke
 * @version 30-mrt-2016
 */
public class MoneyQuestionComponent extends FormattedTextFieldComponent {
    
    /**
     * TODO document
     */
    public static final int NUMBER_OF_COLUMNS = 10;
    
    /**
     * TODO document
     */
    public static final String FORMAT_DESCRIPTION = "money";
    
    /**
     * Decimal separator used in {@link java.math.BigDecimal BigDecimal}, which
     * is not a constant in that class.
     */
    public static final char BIG_DECIMAL_SEPARATOR = '.';
    
    /**
     * TODO document
     */
    public static final int MAX_FRACTION_DIGITS_IN_CURRENCY = 2;
    
    /**
     * TODO document
     * 
     * @param conditionForDisplay
     * @param theQuestion
     * @param theAnswerTable 
     */
    public MoneyQuestionComponent(Expr conditionForDisplay,
            MoneyQuestion theQuestion, AnswerTable theAnswerTable) {
        super(conditionForDisplay, theQuestion, theAnswerTable,
                createNumberFormat(), NUMBER_OF_COLUMNS,
                FORMAT_DESCRIPTION);
    }
    
    /**
     * 
     * @param e 
     */
    @Override
    public void setValue(PropertyChangeEvent e) {
        assert e != null;
        String newValueAsString = getTextFieldContents();
        //remove automatically added thousands seperators
        newValueAsString = standardizeNumberFormat(newValueAsString);
        MoneyValue newValue;
        if (newValueAsString.isEmpty()) {
            newValue = new MoneyValue(null);
        }
        else {
            newValue = new MoneyValue(newValueAsString);
        }
        setValue(newValue);
    }
    
    /**
     * 
     * @param newValue 
     */
    @Override
    public void displayValue(Value newValue) {
        assert newValue instanceof MoneyValue;
        MoneyValue moneyValue = (MoneyValue) newValue;
        BigDecimal valueAsBigDecimal = moneyValue.getValue();
        setTextFieldValue(valueAsBigDecimal);
    }
    
    /**
     * TODO document
     * 
     * @param input
     * @return 
     */
    public String standardizeNumberFormat(String input) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        char thousandsSeparatorChar = symbols.getGroupingSeparator();
        String thousandsSeparator = Character.toString(thousandsSeparatorChar);
        String inputWithoutThousandsSeparators = input.replace(thousandsSeparator, "");
        char decimalSeparator = symbols.getDecimalSeparator();
        return inputWithoutThousandsSeparators.replace(decimalSeparator, BIG_DECIMAL_SEPARATOR);
    }
    
    /**
     * 
     */
    private static NumberFormat createNumberFormat() {
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(MAX_FRACTION_DIGITS_IN_CURRENCY);
        return numberFormat;
    }
}
