package nl.uva.sea.ql.answerTable;

import java.util.Objects;

/**
 * Objects of this class represent values of QL
 * {@link nl.uva.sea.ql.ast.question.DecimalQuestion DecimalQuestion}s.
 * 
 * @author Olav Trauschke
 * @version 30-mar-2016
 */
public class DecimalValue extends NumericValue {
    
    /**
     * Start value used to calculate hashes for objects of this class.
     */
    public static final int HASH_ORIGIN = 623;
    
    private final Double value;
    
    /**
     * Constructor for objects of this class.
     * 
     * @param theValue a decimal value or <code>null</code> to represent an
     *                  unknown value
     */
    public DecimalValue(Double theValue) {
        value = theValue;
    }
    
    /**
     * TODO document
     */
    public Double getValue() {
        return value;
    }
    
    /**
     * Test whether <code>this DecimalValue</code> equals another
     * <code>NumericValue</code> according to ternary logic. Dispatches to a
     * more specific method of <code>other</code>.
     * 
     * @param other a <code>NumericValue</code> to compare to this one
     * @return a <code>BooleanValue</code> representing an unknown value if
     *          <code>this DecimalValue</code> or <code>other</code> represents
     *          an unknown value, <code>true</code> if this is not the case and
     *          <code>this DecimalValue</code> and <code>other</code> represent
     *          equal values and <code>false</code> otherwise
     */
    @Override
    protected BooleanValue hasEqualValue(NumericValue other) {
        return other.hasEqualValue(this); //double dispatch to a more specific case
    }
    
    /**
     * Test whether <code>this DecimalValue</code> equals another according to
     * ternary logic.
     * 
     * @param other a <code>DecimalValue</code> to compare to <code>this one</code>
     * @return a <code>BooleanValue</code> representing an unknown value if
     *          <code>this DecimalValue</code> or <code>other</code> represents
     *          an unknown value, <code>true</code> if this is not the case and
     *          <code>this DecimalValue</code> and <code>other</code> represent
     *          equal values and <code>false</code> otherwise
     */
    @Override
    protected BooleanValue hasEqualValue(DecimalValue other) {
        if (value == null || other.value == null) {
            return new BooleanValue(null);
        }
        boolean equalValues = value.equals(other.value);
        return new BooleanValue(equalValues);
    }
    
    /**
     * Test whether <code>this DecimalValue</code> equals a specified
     * <code>IntValue</code> according to ternary logic.
     * 
     * @param other an <code>IntValue</code> to compare to
     *              <code>this DecimalValue</code>
     * @return a <code>BooleanValue</code> representing an unknown value if
     *          <code>this DecimalValue</code> or <code>other</code> represents
     *          an unknown value, <code>true</code> if this is not the case and
     *          <code>this DecimalValue</code> and <code>other</code> represent
     *          equal values and <code>false</code> otherwise
     */
    @Override
    protected BooleanValue hasEqualValue(IntValue other) {
        return hasEqualValue(other.castDecimal());
    }
    
    /**
     * Test whether <code>this DecimalValue</code> equals a specified
     * <code>MoneyValue</code> according to ternary logic.
     * 
     * @param other a <code>MoneyValue</code> to compare to
     *              <code>this DecimalValue</code>
     * @return a <code>BooleanValue</code> representing an unknown value if
     *          <code>this DecimalValue</code> or <code>other</code> represents
     *          an unknown value, <code>true</code> if this is not the case and
     *          <code>this DecimalValue</code> and <code>other</code> represent
     *          equal values and <code>false</code> otherwise
     */
    @Override
    protected BooleanValue hasEqualValue(MoneyValue other) {
        return other.hasEqualValue(this);
    }
    
    /**
     * Test whether <code>this DecimalValue</code> is greater than a specified
     * <code>NumericValue</code> according to ternary logic. Dispatches to a
     * more specific case.
     * 
     * @param other a <code>NumericValue</code> to compare to this
     *              <code>DecimalValue</code>
     * @return a <code>BooleanValue</code> representing an unknown value if
     *          <code>this DecimalValue</code> or <code>other</code> represents
     *          an unknown value, <code>true</code> if this is not the case and
     *          <code>this DecimalValue</code> is greater than <code>other</code>
     *          and <code>false</code> otherwise
     */
    @Override
    public BooleanValue ternaryGreaterThan(NumericValue other) {
        return other.ternaryGreaterThan(this); //double dispatch to a more specific case
    }
    
    /**
     * Test whether <code>this DecimalValue</code> is greater than another
     * according to ternary logic.
     * 
     * @param other a <code>DecimalValue</code> to compare to <code>this one</code>
     * @return a <code>BooleanValue</code> representing an unknown value if
     *          <code>this DecimalValue</code> or <code>other</code> represents
     *          an unknown value, <code>true</code> if this is not the case and
     *          <code>this DecimalValue</code> is greater than <code>other</code>
     *          and <code>false</code> otherwise
     */
    @Override
    protected BooleanValue ternaryGreaterThan(DecimalValue other) {
        if (value == null || other.value == null) {
            return new BooleanValue(null);
        }
        boolean greaterThan = value > other.value;
        return new BooleanValue(greaterThan);
    }
    
    /**
     * Test whether <code>this DecimalValue</code> is greater than a specified
     * <code>IntValue</code> according to ternary logic.
     * 
     * @param other an <code>IntValue</code> to compare to
     *              <code>this DecimalValue</code>
     * @return a <code>BooleanValue</code> representing an unknown value if
     *          <code>this DecimalValue</code> or <code>other</code> represents
     *          an unknown value, <code>true</code> if this is not the case and
     *          <code>this DecimalValue</code> is greater than <code>other</code>
     *          and <code>false</code> otherwise
     */
    @Override
    protected BooleanValue ternaryGreaterThan(IntValue other) {
        return ternaryGreaterThan(other.castDecimal());
    }
    
    /**
     * Test whether <code>this DecimalValue</code> is greater than a specified
     * <code>MoneyValue</code> according to ternary logic.
     * 
     * @param other a <code>MoneyValue</code> to compare to
     *              <code>this DecimalValue</code>
     * @return a <code>BooleanValue</code> representing an unknown value if
     *          <code>this DecimalValue</code> or <code>other</code> represents
     *          an unknown value, <code>true</code> if this is not the case and
     *          <code>this DecimalValue</code> is greater than <code>other</code>
     *          and <code>false</code> otherwise
     */
    @Override
    protected BooleanValue ternaryGreaterThan(MoneyValue other) {
        return other.ternaryGreaterThan(this);
    }
    
    /**
     * Add a specified <code>NumericValue</code> to <code>this DecimalValue</code>.
     * Dispatches to a more specific case.
     * 
     * @param other a <code>NumericValue</code> to add to
     *              <code>this DecimalValue</code>
     * @return a <code>NumericValue</code> representing an unknown value if
     *          <code>this DecimalValue</code> or <code>other</code> represents
     *          an unknown value or the result of adding <code>other</code> to
     *          <code>this DecimalValue</code> otherwise
     */
    @Override
    public NumericValue add(NumericValue other) {
        return other.multiply(this); //double dispatch to a more specific case
    }
    
    /**
     * Add another <code>DecimalValue</code> to <code>this DecimalValue</code>.
     * 
     * @param other a <code>DecimalValue</code> to add to this one
     * @return a <code>DecimalValue</code> representing an unknown value if
     *          <code>this DecimalValue</code> or <code>other</code> represents
     *          an unknown value or the result of adding <code>other</code> to
     *          <code>this DecimalValue</code> otherwise
     */
    @Override
    protected DecimalValue add(DecimalValue other) {
        if (value == null || other.value == null) {
            return new DecimalValue(null);
        }
        double result = value + other.value;
        return new DecimalValue(result);
    }
    
    /**
     * Add a specified <code>IntValue</code> to <code>this DecimalValue</code>.
     * 
     * @param other an <code>IntValue</code> to add to <code>this DecimalValue</code>
     * @return a <code>DecimalValue</code> representing an unknown value if
     *          <code>this DecimalValue</code> or <code>other</code> represents
     *          an unknown value or the result of adding <code>other</code> to
     *          <code>this DecimalValue</code> otherwise
     */
    @Override
    protected DecimalValue add(IntValue other) {
        return add(other.castDecimal());
    }
    
    /**
     * Add a specified <code>MoneyValue</code> to <code>this DecimalValue</code>.
     * 
     * @param other a <code>MoneyValue</code> to add to <code>this DecimalValue</code>
     * @return a <code>MoneyValue</code> representing an unknown value if
     *          <code>this DecimalValue</code> or <code>other</code> represents
     *          an unknown value or the result of adding <code>other</code> to
     *          <code>this DecimalValue</code> otherwise
     */
    @Override
    protected MoneyValue add(MoneyValue other) {
        return (MoneyValue) other.add(this);
    }
    
    /**
     * Multiply <code>this DecimalValue</code> by a specified
     * <code>NumericValue</code>. Dispatches to a more specific case.
     * 
     * @param other a <code>NumericValue</code> to multiply this
     *              <code>DecimalValue</code> by
     * @return a <code>NumericValue</code> representing an unknown value if
     *          <code>this DecimalValue</code> or <code>other</code> represents
     *          an unknown value or the result of multiplying these
     *          <code>Value</code>s otherwise
     */
    @Override
    public NumericValue multiply(NumericValue other) {
        return other.multiply(this); //double dispatch to a more specific case
    }
    
    /**
     * Multiply <code>this DecimalValue</code> by another.
     * 
     * @param other a <code>DecimalValue</code> to multiply this one by
     * @return a <code>DecimalValue</code> representing an unkonwn value if
     *          <code>this DecimalValue</code> or <code>other</code> represents
     *          an unknown value or the result of multiplying these
     *          <code>DecimalValue</code>s otherwise
     */
    @Override
    protected DecimalValue multiply(DecimalValue other) {
        if (value == null || other.value == null) {
            return new DecimalValue(null);
        }
        double result = value * other.value;
        return new DecimalValue(result);
    }
    
    /**
     * Multiply <code>this DecimalValue</code> by a specified <code>IntValue</code>.
     * 
     * @param other an <code>IntValue</code> to multiply
     *              <code>this DecimalValue</code> by
     * @return a <code>DecimalValue</code> representing an unkonwn value if
     *          <code>this DecimalValue</code> or <code>other</code> represents
     *          an unknown value or the result of multiplying these
     *          <code>Value</code>s otherwise
     */
    @Override
    protected DecimalValue multiply(IntValue other) {
        return multiply(other.castDecimal());
    }
    
    /**
     * Multiply <code>this DecimalValue</code> by a specified
     * <code>MoneyValue</code>.
     * 
     * @param other a <code>MoneyValue</code> to multiply 
     *              <code>this DecimalValue</code> by
     * @return a <code>MoneyValue</code> representing an unkonwn value if
     *          <code>this DecimalValue</code> or <code>other</code> represents
     *          an unknown value or the result of multiplying these
     *          <code>Value</code>s otherwise
     */
    @Override
    protected MoneyValue multiply(MoneyValue other) {
        return (MoneyValue) other.multiply(this);
    }
    
    /**
     * Divide <code>this DecimalValue</code> by a specified <code>NumericValue</code>.
     * Dispatches to a more specific case.
     * 
     * @param other a <code>NumericValue</code> to divide
     *              <code>this DecimalValue</code> by
     * @return a <code>DecimalValue</code> representing an unknown value if
     *          <code>this DecimalValue</code> or <code>other</code> represents
     *          an unknown value or the result of dividing
     *          <code>this DecimalValue</code> by <code>other</code> otherwise
     */
    @Override
    public DecimalValue divide(NumericValue other) {
        return other.inverseDivide(this); //double dispatch to a more specific case
    }
    
    /**
     * Divide a specified <code>DecimalValue</code> by this one.
     * 
     * @param other a <code>DecimalValue</code> to divide by
     *              <code>this DecimalValue</code>
     * @return a <code>DecimalValue</code> representing an unknown value if
     *          <code>this DecimalValue</code> or <code>other</code> represents
     *          an unknown value or the result of dividing
     *          <code>other</code> by <code>this DecimalValue</code> otherwise
     */
    @Override
    protected DecimalValue inverseDivide(DecimalValue other) {
        if (value == null || other.value == null) {
            return new DecimalValue(null);
        }
        double result = other.value / value;
        return new DecimalValue(result);
    }
    
    /**
     * Divide a specified <code>IntValue</code> by <code>this DecimalValue</code>.
     * 
     * @param other an <code>IntValue</code> to divide by
     *              <code>this DecimalValue</code>
     * @return a <code>DecimalValue</code> representing an unknown value if
     *          <code>this DecimalValue</code> or <code>other</code> represents
     *          an unknown value or the result of dividing
     *          <code>other</code> by <code>this DecimalValue</code> otherwise
     */
    @Override
    protected DecimalValue inverseDivide(IntValue other) {
        return inverseDivide(other.castDecimal());
    }
    
    /**
     * Divide a specified <code>MoneyValue</code> by <code>this DecimalValue</code>.
     * 
     * @param other a <code>MoneyValue</code> to divide by
     *              <code>this DecimalValue</code>
     * @return a <code>DecimalValue</code> representing an unkonwn value if
     *          <code>this DecimalValue</code> or <code>other</code> represents
     *          an unkonwn value or the result of dividing <code>other</code> by
     *          <code>this DecimalValue</code> otherwise
     */
    @Override
    protected MoneyValue inverseDivide(MoneyValue other) {
        return other.divide(this);
    }
    
    /**
     * Obtain a <code>MoneyValue</code> representing <code>theValue</code>
     * of <code>this DecimalValue</code>.
     * 
     * @return a <code>MoneyValue</code> representing the same value as
     *          <code>this DecimalValue</code>
     */
    protected MoneyValue castMoney() {
        return new MoneyValue(value);
    }
    
    /**
     * Compares <code>this DecimalValue</code> to another <code>Object</code>.
     * 
     * @param o the <code>Object</code> to compare to <code>this DecimalValue</code>
     * @return <code>true</code> if and only if <code>o</code> is a
     *          <code>DecimalValue</code> with the same <code>value</code> as
     *          <code>this DecimalValue</code> 
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        
        DecimalValue other = (DecimalValue) o;
        return value == null ? other.value == null : value.equals(other.value);
    }

    /**
     * @return an <code>int</code> containing a hash for <code>this DecimalValue</code> 
     */
    @Override
    public int hashCode() {
        return HASH_ORIGIN + Objects.hashCode(this.value);
    }
    
}
