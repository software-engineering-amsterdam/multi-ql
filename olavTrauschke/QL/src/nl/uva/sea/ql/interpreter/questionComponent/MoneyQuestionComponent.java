package nl.uva.sea.ql.interpreter.questionComponent;

import java.beans.PropertyChangeEvent;
import java.math.BigDecimal;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import nl.uva.sea.ql.answerTable.*;
import nl.uva.sea.ql.ast.expr.Expr;
import nl.uva.sea.ql.ast.question.MoneyQuestion;

/**
 * Objects of this class are <code>FormattedTextFieldComponent</code>s that are
 * used to display <code>MoneyQuestion</code>s.
 * 
 * @author Olav Trauschke
 * @version 1-apr-2016
 */
public class MoneyQuestionComponent extends FormattedTextFieldComponent {
    
    /**
     * An <code>int</code> expressing the number of characters that fit in
     * a <code>MoneyQuestionComponent</code>.
     */
    public static final int NUMBER_OF_COLUMNS = 10;
    
    /**
     * Decimal separator used in {@link java.math.BigDecimal BigDecimal}, which
     * is not a constant in that class.
     */
    public static final char BIG_DECIMAL_SEPARATOR = '.';
    
    private static final DecimalFormatSymbols SYMBOLS = new DecimalFormatSymbols();
    
    /**
     * Constructor for objects of this class.
     * 
     * @param conditionForDisplay an <code>Expr</code> defining when the constructed
     *                              <code>MoneyQuestionComponent</code>
     *                              should be displayed
     * @param theQuestion a <code>MoneyQuestion</code> that should be displayed
     *                      when <code>conditionToDisplay</code> evaluates to
     *                      <code>true</code>
     * @param theAnswerTable an <code>AnswerTable</code> mapping all
     *                      <code>Ident</code>s <code>conditionToDisplay</code>
     *                      or <code>theCalculation</code> of <code>theQuestion</code>
     *                      could contain to their current <code>Value</code>s,
     *                      or <code>null</code> when these are unknown
     */
    public MoneyQuestionComponent(Expr conditionForDisplay,
            MoneyQuestion theQuestion, AnswerTable theAnswerTable) {
        super(conditionForDisplay, theQuestion, theAnswerTable,
                createNumberFormat(), NUMBER_OF_COLUMNS,
                SYMBOLS.getCurrencySymbol());
    }
    
    /**
     * Set the value of <code>this IntQuestionComponent</code>'s
     * <code>question</code> to the value currently written in it.
     * 
     * @param e an <code>ActionEvent</code> that changes the value of
     *          <code>this MoneyQuestionComponent</code>
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
     * Make <code>this MoneyQuestionComponent</code> display a specified
     * <code>Value</code>.
     * 
     * @param newValue a <code>Value this MoneyQuestionComponent</code> should
     *                  display
     */
    @Override
    public void displayValue(Value newValue) {
        assert newValue instanceof MoneyValue;
        MoneyValue moneyValue = (MoneyValue) newValue;
        BigDecimal valueAsBigDecimal = moneyValue.getValue();
        setTextFieldValue(valueAsBigDecimal);
    }
    
    /**
     * Standardize a <code>String</code> that represents a number to a format
     * accepted by
     * {@link nl.uva.sea.ql.answerTable.MoneyValue#MoneyValue(java.lang.String)
     * MoneyValue(String)}. Removes thousands separators and changes decimal
     * separators to <code>BIG_DECIMAL_SEPARATOR</code>.
     * 
     * @param input a <code>String</code> representing a number that should be
     *              standardized
     * @return a <code>String</code> representing the same value as <code>input</code>
     *          but in a standardized format
     */
    public String standardizeNumberFormat(String input) {
        char thousandsSeparatorChar = SYMBOLS.getGroupingSeparator();
        String thousandsSeparator = Character.toString(thousandsSeparatorChar);
        String inputWithoutThousandsSeparators = input.replace(thousandsSeparator, "");
        char decimalSeparator = SYMBOLS.getDecimalSeparator();
        return inputWithoutThousandsSeparators.replace(decimalSeparator, BIG_DECIMAL_SEPARATOR);
    }
    
    /**
     * @return a <code>NumberFormat</code> accepting no more fraction digits
     *          than the limit defined by the <code>currency</code> of
     *          <code>SYMBOLS</code>
     */
    private static NumberFormat createNumberFormat() {
        NumberFormat numberFormat = NumberFormat.getInstance();
        int maxFractionDigits = SYMBOLS.getCurrency().getDefaultFractionDigits();
        numberFormat.setMaximumFractionDigits(maxFractionDigits);
        return numberFormat;
    }
}
