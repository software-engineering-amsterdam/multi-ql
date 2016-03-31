package nl.uva.sea.ql.answerTable;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Objects of this class represent values of QL
 * {@link nl.uva.sea.ql.ast.question.MoneyQuestion DecimalQuestion}s.
 * 
 * @author Olav Trauschke
 * @version 1-apr-2016
 */
public class MoneyValue extends NumericValue {
    
    /**
     * Start value used to calculate hashes for objects of this class.
     */
    public static final int HASH_ORIGIN = 203;
    
    private final BigDecimal value;
    
    /**
     * Constructor for objects of this class.
     * 
     * @param theValue a <code>String</code> representing the value in the
     *                  format defined by {@link java.math.BigDecimal BigDecimal},
     *                  or <code>null</code> to represent an unknown value
     */
    public MoneyValue(String theValue) {
        if (theValue == null) {
            value = null;
        }
        else {
            value = new BigDecimal(theValue);
        }
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
     * @return a <code>BigDecimal</code> with the <code>value this MoneyValue</code>
     *          represents
     */
    public BigDecimal getValue() {
        return value;
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
     * Add a specified <code>NumericValue</code> to <code>this MoneyValue</code>.
     * 
     * @param other a <code>NumericValue</code> to add to
     *              <code>this MoneyValue</code>
     * @return a <code>MoneyValue</code> representing an unknown value if
     *          <code>this MoneyValue</code> or <code>other</code> represents
     *          an unknown value or the result of adding <code>other</code> to
     *          <code>this MoneyValue</code> otherwise
     */
    @Override
    public MoneyValue add(NumericValue other) {
        return other.add(this);
    }
    
    /**
     * Add a specified <code>DecimalValue</code> to <code>this MoneyValue</code>.
     * 
     * @param other a <code>DecimalValue</code> to add to <code>this MoneyValue</code>
     * @return a <code>MoneyValue</code> representing an unkonwn value if
     *          <code>this MoneyValue</code> or <code>other</code> represents an
     *          unkonwn value or the result of adding <code>other</code> to
     *          <code>this MoneyValue</code> otherwise
     */
    @Override
    protected MoneyValue add(DecimalValue other) {
        return add(other.castMoney());
    }
    
    /**
     * Add a specified <code>IntValue</code> to <code>this MoneyValue</code>.
     * 
     * @param other an <code>IntValue</code> to add to <code>this MoneyValue</code>
     * @return a <code>MoneyValue</code> representing an unkonwn value if
     *          <code>this MoneyValue</code> or <code>other</code> represents an
     *          unkonwn value or the result of adding <code>other</code> to
     *          <code>this MoneyValue</code> otherwise
     */
    @Override
    protected MoneyValue add(IntValue other) {
        return add(other.castMoney());
    }
    
    /**
     * Add a specified <code>MoneyValue</code> to this one.
     * 
     * @param other a <code>MoneyValue</code> to add to this one
     * @return a <code>MoneyValue</code> representing an unkonwn value if
     *          <code>this MoneyValue</code> or <code>other</code> represents an
     *          unkonwn value or the result of adding <code>other</code> to
     *          <code>this MoneyValue</code> otherwise
     */
    @Override
    protected MoneyValue add(MoneyValue other) {
        if (value == null || other.value == null) {
            return new MoneyValue(null);
        }
        BigDecimal result = value.add(other.value);
        return new MoneyValue(result.toString());
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
        return other.multiply(this); //double dispatch to a more specific case
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
    
    /**
     * Divide <code>this MoneyValue</code> by a specified <code>NumericValue</code>.
     * Dispatches to a more specific case.
     * 
     * @param other a <code>NumericValue</code> to divide
     *              <code>this MoneyValue</code> by
     * @return a <code>NumericValue</code> representing an unknown value if
     *          <code>this MoneyValue</code> or <code>other</code> represents
     *          an unknown value or the result of dividing
     *          <code>this MoneyValue</code> by <code>other</code> otherwise
     */
    @Override
    public NumericValue divide(NumericValue other) {
        return other.inverseDivide(this); //double dispatch to a more specific case
    }
    
    /**
     * Divide <code>this MoneyValue</code> by a specified <code>DecimalValue</code>.
     * 
     * @param other a <code>DecimalValue</code> to divide
     *              <code>this MoneyValue</code> by
     * @return a <code>DecimalValue</code> representing an unknown value if
     *          <code>this MoneyValue</code> or <code>other</code> represents
     *          an unknown value or the result of dividing
     *          <code>this MoneyValue</code> by <code>other</code> otherwise
     */
    protected MoneyValue divide(DecimalValue other) {
        DecimalValue decimalValue = castDecimal();
        DecimalValue result = other.inverseDivide(decimalValue);
        return result.castMoney();
    }
    
    /**
     * Divide a specified <code>DecimalValue</code> by <code>this MoneyValue</code>.
     * 
     * @param other a <code>DecimalValue</code> to divide by
     *              <code>this MoneyValue</code>
     * @return a <code>DecimalValue</code> representing an unknown value if
     *          <code>this MoneyValue</code> or <code>other</code> represents
     *          an unknown value or the result of dividing
     *          <code>other</code> by <code>this MoneyValue</code> otherwise
     */
    @Override
    protected DecimalValue inverseDivide(DecimalValue other) {
        return inverseDivide(other.castMoney());
    }
    
    /**
     * Divide a specified <code>IntValue</code> by <code>this MoneyValue</code>.
     * 
     * @param other an <code>IntValue</code> to divide by
     *              <code>this MoneyValue</code>
     * @return a <code>DecimalValue</code> representing an unknown value if
     *          <code>this MoneyValue</code> or <code>other</code> represents
     *          an unknown value or the result of dividing
     *          <code>other</code> by <code>this MoneyValue</code> otherwise
     */
    @Override
    protected DecimalValue inverseDivide(IntValue other) {
        return inverseDivide(other.castMoney());
    }
    
    /**
     * Divide a specified <code>MoneyValue</code> by this one.
     * 
     * @param other a <code>MoneyValue</code> to divide by this one
     * @return a <code>DecimalValue</code> representing an unknown value if
     *          <code>this MoneyValue</code> or <code>other</code> represents
     *          an unknown value or the result of dividing
     *          <code>other</code> by <code>this MoneyValue</code> otherwise
     */
    @Override
    protected DecimalValue inverseDivide(MoneyValue other) {
        BigDecimal result = other.value.divide(value);
        return new DecimalValue(result.doubleValue());
    }
    
    /**
     * Obtain a <code>DecimalValue</code> representing <code>theValue</code> of
     * <code>this MoneyValue</code>.
     * 
     * @return a <code>DecimalValue</code> representing approximatly the same
     *          value as <code>this MoneyValue</code>
     */
    protected DecimalValue castDecimal() {
        if (value == null) {
            return new DecimalValue(null);
        }
        return new DecimalValue(value.doubleValue());
    }
    
    /**
     * Compares <code>this MoneyValue</code> to another <code>Object</code>.
     * 
     * @param o the <code>Object</code> to compare to <code>this MoneyValue</code>
     * @return <code>true</code> if and only if <code>o</code> is a
     *          <code>MoneyValue</code> with the same <code>value</code> as
     *          <code>this MoneyValue</code> 
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        
        MoneyValue other = (MoneyValue) o;
        return value == null ? other.value == null : value.equals(other.value);
    }

    /**
     * @return an <code>int</code> containing a hash for <code>this MoneyValue</code> 
     */
    @Override
    public int hashCode() {
        return HASH_ORIGIN + Objects.hashCode(this.value);
    }
    
}
