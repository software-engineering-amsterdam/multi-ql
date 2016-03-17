package nl.uva.sea.ql.answerTable;

import java.math.BigDecimal;

/**
 * Objects of this class represent values of QL
 * {@link nl.uva.sea.ql.ast.question.MoneyQuestion DecimalQuestion}s.
 * 
 * @author Olav Trauschke
 * @version 17-mar-2016
 */
public class MoneyValue extends NumericValue {
    
    private final BigDecimal value;
    
    /**
     * Constructor for objects of this class.
     * 
     * @param theValue a <code>String</code> representing the value, or
     *                  <code>null</code> to represent an unknown value
     */
    public MoneyValue(String theValue) {
        value = new BigDecimal(theValue);
    }
    
    /**
     * Constructor for objects of this class based on <code>double</code> values.
     * This constructor should be used for unsafe casting of
     * <code>double</code>s only.
     * 
     * @param theValue a <code>double</code> the constructed <code>MoneyValue</code>
     *                  should represent
     */
    protected MoneyValue(double theValue) {
        value = new BigDecimal(theValue);
    }
    
    /**
     * Test whether <code>this MoneyValue</code> equals another
     * <code>NumericValue</code> according to ternary logic. Dispatches to a
     * more specific method of <code>other</code>.
     * 
     * @param other a <code>NumericValue</code> to compare to this one
     * @return a <code>BooleanValue</code> representing an unknown value if
     *          <code>this MoneyValue</code> or <code>other</code> represents an
     *          unknown value, <code>true</code> if this is not the case and
     *          <code>this MoneyValue</code> and <code>other</code> represent
     *          equal values and <code>false</code> otherwise
     */
    @Override
    protected BooleanValue hasEqualValue(NumericValue other) {
        return other.hasEqualValue(this); //double dispatch to a more specific case
    }
    
    /**
     * Test whether <code>this MoneyValue</code> equals a specified
     * <code>DecimalValue</code> according to ternary logic.
     * 
     * @param other a <code>DecimalValue</code> to compare to
     *              <code>this MoneyValue</code>
     * @return a <code>BooleanValue</code> representing an unknown value if
     *          <code>this MoneyValue</code> or <code>other</code> represents an
     *          unknown value, <code>true</code> if this is not the case and
     *          <code>this MoneyValue</code> and <code>other</code> represent
     *          equal values and <code>false</code> otherwise
     */
    @Override
    protected BooleanValue hasEqualValue(DecimalValue other) {
        return hasEqualValue(other.castMoney());
    }
    
    /**
     * Test whether <code>this MoneyValue</code> equals a specified
     * <code>IntValue</code> according to ternary logic.
     * 
     * @param other an <code>IntValue</code> to compare to
     *              <code>this MoneyValue</code>
     * @return a <code>BooleanValue</code> representing an unknown value if
     *          <code>this MoneyValue</code> or <code>other</code> represents an
     *          unknown value, <code>true</code> if this is not the case and
     *          <code>this MoneyValue</code> and <code>other</code> represent
     *          equal values and <code>false</code> otherwise
     */
    @Override
    protected BooleanValue hasEqualValue(IntValue other) {
        return hasEqualValue(other.castMoney());
    }
    
    /**
     * Test whether <code>this MoneyValue</code> equals another according to
     * ternary logic.
     * 
     * @param other a <code>MoneyValue</code> to compare to this one
     * @return a <code>BooleanValue</code> representing an unknown value if
     *          <code>this MoneyValue</code> or <code>other</code> represents an
     *          unknown value, <code>true</code> if this is not the case and
     *          <code>this MoneyValue</code> and <code>other</code> represent
     *          equal values and <code>false</code> otherwise
     */
    @Override
    protected BooleanValue hasEqualValue(MoneyValue other) {
        if (value == null || other.value == null) {
            return new BooleanValue(null);
        }
        boolean equalValues = value.compareTo(other.value) == 0;
        return new BooleanValue(equalValues);
    }
    
    /**
     * Test whether <code>this MoneyValue</code> is greater than a specified
     * <code>NumericValue</code> according to ternary logic. Dispatches to a
     * more specific case.
     * 
     * @param other a <code>NumericValue</code> to compare to
     *              <code>this MoneyValue</code>
     * @return a <code>BooleanValue</code> representing an unknown value if
     *          <code>this MoneyValue</code> or <code>other</code> represents
     *          an unknown value, <code>true</code> if this is not the case and
     *          <code>this MoneyValue</code> is greater than <code>other</code>
     *          and <code>false</code> otherwise
     */
    @Override
    public BooleanValue ternaryGreaterThan(NumericValue other) {
        return other.ternaryGreaterThan(this); //Double dispatch to a more specific case
    }
    
    /**
     * Test whether <code>this MoneyValue</code> is greater than a specified
     * <code>DecimalValue</code> according to ternary logic.
     * 
     * @param other a <code>DecimalValue</code> to compare to
     *              <code>this MoneyValue</code>
     * @return a <code>BooleanValue</code> representing an unknown value if
     *          <code>this MoneyValue</code> or <code>other</code> represents
     *          an unknown value, <code>true</code> if this is not the case and
     *          <code>this MoneyValue</code> is greater than <code>other</code>
     *          and <code>false</code> otherwise
     */
    @Override
    protected BooleanValue ternaryGreaterThan(DecimalValue other) {
        return ternaryGreaterThan(other.castMoney());
    }
    
    /**
     * Test whether <code>this MoneyValue</code> is greater than a specified
     * <code>IntValue</code> according to ternary logic.
     * 
     * @param other an <code>IntValue</code> to compare to
     *              <code>this MoneyValue</code>
     * @return a <code>BooleanValue</code> representing an unknown value if
     *          <code>this MoneyValue</code> or <code>other</code> represents
     *          an unknown value, <code>true</code> if this is not the case and
     *          <code>this MoneyValue</code> is greater than <code>other</code>
     *          and <code>false</code> otherwise
     */
    @Override
    protected BooleanValue ternaryGreaterThan(IntValue other) {
        return ternaryGreaterThan(other.castMoney());
    }
    
    /**
     * Test whether <code>this MoneyValue</code> is greater than another
     * according to ternary logic.
     * 
     * @param other a <code>MoneyValue</code> to compare to
     *              <code>this MoneyValue</code>
     * @return a <code>BooleanValue</code> representing an unknown value if
     *          <code>this MoneyValue</code> or <code>other</code> represents
     *          an unknown value, <code>true</code> if this is not the case and
     *          <code>this MoneyValue</code> is greater than <code>other</code>
     *          and <code>false</code> otherwise
     */
    @Override
    protected BooleanValue ternaryGreaterThan(MoneyValue other) {
        if (value == null || other.value == null) {
            return new BooleanValue(null);
        }
        boolean equalValues = value.compareTo(other.value) > 0;
        return new BooleanValue(equalValues);
    }
    
    /**
     * Multiply <code>this MoneyValue</code> by a specified
     * <code>MoneyValue</code>. Dispatches to a more specific case.
     * 
     * @param other a <code>NumericValue</code> to multiply
     *              <code>this MoneyValue</code> by
     * @return a <code>NumericValue</code> representing an unknown value if
     *          <code>this MoneyValue</code> or <code>other</code> represents
     *          an unknown value or the result of multiplying these
     *          <code>Value</code>s otherwise
     */
    @Override
    public MoneyValue multiply(NumericValue other) {
        return other.multiply(this);
    }
    
    /**
     * Multiply <code>this MoneyValue</code> by a specified
     * <code>DecimalValue</code>.
     * 
     * @param other a <code>DecimalValue</code> to multiply
     *              <code>this MoneyValue</code> by
     * @return a <code>MoneyValue</code> representing an unknown value if
     *          <code>this MoneyValue</code> or <code>other</code> represents
     *          an unknown value or the result of multiplying these
     *          <code>Value</code>s otherwise
     */
    @Override
    protected MoneyValue multiply(DecimalValue other) {
        return multiply(other.castMoney());
    }
    
    /**
     * Multiply <code>this MoneyValue</code> by a specified
     * <code>IntValue</code>.
     * 
     * @param other an <code>IntValue</code> to multiply
     *              <code>this MoneyValue</code> by
     * @return a <code>MoneyValue</code> representing an unknown value if
     *          <code>this MoneyValue</code> or <code>other</code> represents
     *          an unknown value or the result of multiplying these
     *          <code>Value</code>s otherwise
     */
    @Override
    protected MoneyValue multiply(IntValue other) {
        return multiply(other.castMoney());
    }
    
    /**
     * Multiply <code>this MoneyValue</code> by another.
     * 
     * @param other a <code>MoneyValue</code> to multiply this one by
     * @return a <code>MoneyValue</code> representing an unknown value if
     *          <code>this MoneyValue</code> or <code>other</code> represents
     *          an unknown value or the result of multiplying these
     *          <code>MoneyValue</code>s otherwise
     */
    @Override
    protected MoneyValue multiply(MoneyValue other) {
        if (value == null || other.value == null) {
            return new MoneyValue(null);
        }
        BigDecimal result = value.multiply(other.value);
        return new MoneyValue(result.toString());
    }
    
}
