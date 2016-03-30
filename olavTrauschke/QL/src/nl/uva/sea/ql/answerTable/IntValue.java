package nl.uva.sea.ql.answerTable;

import java.util.Objects;

/**
 * Objects of this class represent values of QL
 * {@link nl.uva.sea.ql.ast.question.IntQuestion IntQuestion}s and
 * {@link nl.uva.sea.ql.ast.expr.Expr Expr}s with an integer value.
 * 
 * @author Olav Trauschke
 * @version 30-mar-2016
 */
public class IntValue extends NumericValue {
    
    /**
     * Start value used to calculate hashes for objects of this class.
     */
    public static final int HASH_ORIGIN = 483;
    
    private final Long value;
    
    /**
     * Constructor for objects of this class.
     * 
     * @param theValue a <code>Long</code> value or <code>null</code> to represent
     *                  an unknown value
     */
    public IntValue(Long theValue) {
        value = theValue;
    }
    
    /**
     * TODO document
     * 
     * @return 
     */
    public Long getValue() {
       return value; 
    }
    
    /**
     * Test whether <code>this IntValue</code> equals another
     * <code>NumericValue</code> according to ternary logic. Dispatches to a
     * more specific method of <code>other</code>.
     * 
     * @param other a <code>NumericValue</code> to compare to this one
     * @return a <code>BooleanValue</code> representing an unknown value if
     *          <code>this IntValue</code> or <code>other</code> represents an
     *          unknown value, <code>true</code> if this is not the case and
     *          <code>this IntValue</code> and <code>other</code> represent equal
     *          values and <code>false</code> otherwise
     */
    @Override
    protected BooleanValue hasEqualValue(NumericValue other) {
        return other.hasEqualValue(this); //double dispatch to get to a more specific case
    }
    
    /**
     * Test whether <code>this IntValue</code> equals a specified
     * <code>DecimalValue</code> according to ternary logic.
     * 
     * @param other a <code>DecimalValue</code> to compare to
     *              <code>this IntValue</code>
     * @return a <code>BooleanValue</code> representing an unknown value if
     *          <code>this IntValue</code> or <code>other</code> represents an
     *          unknown value, <code>true</code> if this is not the case and
     *          <code>this IntValue</code> and <code>other</code> represent equal
     *          values and <code>false</code> otherwise
     */
    @Override
    protected BooleanValue hasEqualValue(DecimalValue other) {
        return other.hasEqualValue(this);
    }
    
    /**
     * Test whether <code>this IntValue</code> equals another according to
     * ternary logic.
     * 
     * @param other an <code>IntValue</code> to compare to this one
     * @return a <code>BooleanValue</code> representing an unknown value if
     *          <code>this IntValue</code> or <code>other</code> represents an
     *          unknown value, <code>true</code> if this is not the case and
     *          <code>this IntValue</code> and <code>other</code> represent equal
     *          values and <code>false</code> otherwise
     */
    @Override
    protected BooleanValue hasEqualValue(IntValue other) {
        if (value == null || other.value == null) {
            return new BooleanValue(null);
        }
        boolean equalValues = value.equals(other.value);
        return new BooleanValue(equalValues);
    }
    
    /**
     * Test whether <code>this IntValue</code> equals a specified
     * <code>MoneyValue</code> according to ternary logic.
     * 
     * @param other a <code>MoneyValue</code> to compare to
     *              <code>this IntValue</code>
     * @return a <code>BooleanValue</code> representing an unknown value if
     *          <code>this IntValue</code> or <code>other</code> represents an
     *          unknown value, <code>true</code> if this is not the case and
     *          <code>this IntValue</code> and <code>other</code> represent equal
     *          values and <code>false</code> otherwise
     */
    @Override
    protected BooleanValue hasEqualValue(MoneyValue other) {
        return other.hasEqualValue(this);
    }
    
    /**
     * Test whether <code>this IntValue</code> is greater than a specified
     * <code>NumericValue</code> according to ternary logic. Dispatches to a
     * more specific case.
     * 
     * @param other a <code>NumericValue</code> to compare to this
     *              <code>IntValue</code>
     * @return a <code>BooleanValue</code> representing an unknown value if
     *          <code>this IntValue</code> or <code>other</code> represents
     *          an unknown value, <code>true</code> if this is not the case and
     *          <code>this IntValue</code> is greater than <code>other</code>
     *          and <code>false</code> otherwise
     */
    @Override
    public BooleanValue ternaryGreaterThan(NumericValue other) {
        return other.ternaryGreaterThan(this); //double dispatch to a more specific case
    }
    
    /**
     * Test whether <code>this IntValue</code> is greater than a specified
     * <code>DecimalValue</code> according to ternary logic.
     * 
     * @param other a <code>DecimalValue</code> to compare to
     *              <code>this IntValue</code>
     * @return a <code>BooleanValue</code> representing an unknown value if
     *          <code>this IntValue</code> or <code>other</code> represents an
     *          unknown value, <code>true</code> if this is not the case and
     *          <code>this IntValue</code> is greater than <code>other</code>
     *          and <code>false</code> otherwise
     */
    @Override
    public BooleanValue ternaryGreaterThan(DecimalValue other) {
        return other.ternaryGreaterThan(this);
    }
    
    /**
     * Test whether <code>this IntValue</code> is greater than another according
     * to ternary logic.
     * 
     * @param other an <code>IntValue</code> to compare to this one
     * @return a <code>BooleanValue</code> representing an unknown value if
     *          <code>this IntValue</code> or <code>other</code> represents an
     *          unknown value, <code>true</code> if this is not the case and
     *          <code>this IntValue</code> is greater than <code>other</code>
     *          and <code>false</code> otherwise
     */
    @Override
    public BooleanValue ternaryGreaterThan(IntValue other) {
        if (value == null || other.value == null) {
            return new BooleanValue(null);
        }
        boolean greaterThan = value > other.value;
        return new BooleanValue(greaterThan);
    }
    
    /**
     * Test whether <code>this IntValue</code> is greater than a specified
     * <code>MoneyValue</code> according to ternary logic.
     * 
     * @param other a <code>MoneyValue</code> to compare to
     *              <code>this IntValue</code>
     * @return a <code>BooleanValue</code> representing an unknown value if
     *          <code>this IntValue</code> or <code>other</code> represents an
     *          unknown value, <code>true</code> if this is not the case and
     *          <code>this IntValue</code> is greater than <code>other</code>
     *          and <code>false</code> otherwise
     */
    @Override
    public BooleanValue ternaryGreaterThan(MoneyValue other) {
        return other.ternaryGreaterThan(this);
    }
    
    /**
     * Add a specified <code>NumericValue</code> to <code>this IntValue</code>.
     * Dispatches to a more specific case.
     * 
     * @param other a <code>NumericValue</code> to add to <code>this IntValue</code>
     * @return a <code>NumericValue</code> representing an unknown value if
     *          <code>this IntValue</code> or <code>other</code> represents an
     *          unknown value or the result of multiplying these <code>Value</code>s
     *          otherwise
     */
    @Override
    public NumericValue add(NumericValue other) {
        return other.add(this); //double dispatch to a more specific case
    }
    
    /**
     * Add a specified <code>DecimalValue</code> to <code>this IntValue</code>.
     * 
     * @param other a <code>DecimalValue</code> to add to <code>this IntValue</code>
     * @return a <code>DecimalValue</code> representing an unkonwn value if
     *          <code>this IntValue</code> or <code>other</code> represents an
     *          unknown value or the result of adding <code>other</code> to
     *          <code>this DecimalValue</code> otherwise
     */
    @Override
    protected DecimalValue add(DecimalValue other) {
        return other.add(this);
    }
    
    /**
     * Add a specified <code>IntValue</code> to this one.
     * 
     * @param other an <code>IntValue</code> to add to this one
     * @return an <code>IntValue</code> representing an unkonwn value if
     *          <code>this IntValue</code> or <code>other</code> represents an
     *          unknown value or the result of adding <code>other</code> to
     *          <code>this IntValue</code> otherwise
     */
    @Override
    protected IntValue add(IntValue other) {
        if (value == null || other.value == null) {
            return new IntValue(null);
        }
        long result = value + other.value;
        return new IntValue(result);
    }
    
    /**
     * Add a specified <code>MoneyValue</code> to this one.
     * 
     * @param other a <code>MoneyValue</code> to add to this one
     * @return a <code>MoneyValue</code> representing an unkonwn value if
     *          <code>this IntValue</code> or <code>other</code> represents an
     *          unknown value or the result of adding <code>other</code> to
     *          <code>this IntValue</code> otherwise
     */
    @Override
    protected MoneyValue add(MoneyValue other) {
        return (MoneyValue) other.add(this);
    }
    
    /**
     * Multiply <code>this IntValue</code> by a specified
     * <code>NumericValue</code>. Dispatches to a more specific case.
     * 
     * @param other a <code>NumericValue</code> to multiply
     *              <code>this IntValue</code> by
     * @return a <code>NumericValue</code> representing an unknown value if
     *          <code>this IntValue</code> or <code>other</code> represents
     *          an unknown value or the result of multiplying these
     *          <code>Value</code>s otherwise
     */
    @Override
    public NumericValue multiply(NumericValue other) {
        return other.multiply(this); //double dispatch to a more specific case
    }
    
    /**
     * Multiply <code>this IntValue</code> by a specified <code>DecimalValue</code>.
     * 
     * @param other a <code>DecimalValue</code> to multiply
     *              <code>this IntValue</code> by
     * @return a <code>DecimalValue</code> representing an unkonwn value if
     *          <code>this IntValue</code> or <code>other</code> represents
     *          an unknown value or the result of multiplying these
     *          <code>Value</code>s otherwise
     */
    @Override
    protected DecimalValue multiply(DecimalValue other) {
        return other.multiply(this);
    }
    
    /**
     * Multiply <code>this IntValue</code> by another.
     * 
     * @param other an <code>IntValue</code> to multiply this one by
     * @return an <code>IntValue</code> representing an unkonwn value if
     *          <code>this IntValue</code> or <code>other</code> represents
     *          an unknown value or the result of multiplying these
     *          <code>IntValue</code>s otherwise
     */
    @Override
    protected IntValue multiply(IntValue other) {
        if (value == null || other.value == null) {
            return new IntValue(null);
        }
        long result = value * other.value;
        return new IntValue(result);
    }
    
    /**
     * Multiply <code>this IntValue</code> by a specified <code>MoneyValue</code>.
     * 
     * @param other a <code>MoneyValue</code> to multiply
     *              <code>this IntValue</code> by
     * @return a <code>MoneyValue</code> representing an unkonwn value if
     *          <code>this IntValue</code> or <code>other</code> represents
     *          an unknown value or the result of multiplying these
     *          <code>Value</code>s otherwise
     */
    @Override
    protected MoneyValue multiply(MoneyValue other) {
        return (MoneyValue) other.multiply(this);
    }
    
    /**
     * Divide <code>this IntValue</code> by a specified
     * <code>NumericValue</code>. Dispatches to a more specific case.
     * 
     * @param other a <code>NumericValue</code> to divide this
     *              <code>IntValue</code> by
     * @return a <code>NumericValue</code> representing an unknown value if
     *          <code>this IntValue</code> or <code>other</code> represents
     *          an unknown value or the result of dividing <code>this IntValue</code>
     *          by <code>other</code> otherwise
     */
    @Override
    public DecimalValue divide(NumericValue other) {
        return other.inverseDivide(this); //double dispatch to a more specific case
    }
    
    /**
     * Divide a specified <code>DecimalValue</code> by <code>this IntValue</code>.
     * 
     * @param other a <code>DecimalValue</code> to divide by
     *              <code>this IntValue</code>
     * @return a <code>DecimalValue</code> representing an unknown value if
     *          <code>this IntValue</code> or <code>other</code> represents
     *          an unknown value or the result of dividing <code>other</code> by
     *          <code>this IntValue</code> otherwise
     */
    @Override
    protected DecimalValue inverseDivide(DecimalValue other) {
        DecimalValue decimalValue = castDecimal();
        return decimalValue.inverseDivide(other);
    }
    
    /**
     * Divide a specified <code>IntValue</code> by this one.
     * 
     * @param other an <code>IntValue</code> to divide
     *              <code>this IntValue</code> by
     * @return a <code>DecimalValue</code> representing an unknown value if
     *          <code>this IntValue</code> or <code>other</code> represents
     *          an unknown value or the result of dividing
     *          <code>other</code> by <code>this IntValue</code> otherwise
     */
    @Override
    protected DecimalValue inverseDivide(IntValue other) {
        if (value == null || other.value == null) {
            return new DecimalValue(null);
        }
        double result = other.value / (double) value;
        return new DecimalValue(result);
    }
    
    /**
     * Divide a specified <code>MoneyValue</code> by <code>this IntValue</code>.
     * 
     * @param other a <code>MoneyValue</code> to divide by <code>this IntValue</code>
     * @return a <code>DecimalValue</code> reprenting an unkonw value if
     *          <code>this IntValue</code> or <code>other</code> represents an
     *          unknown value or the result of dividing <code>other</code> by
     *          <code>this IntValue</code> otherwise
     */
    @Override
    protected MoneyValue inverseDivide(MoneyValue other) {
        DecimalValue decimalValue = castDecimal();
        return other.divide(decimalValue);
    }
    
    /**
     * Obtain a <code>DecimalValue</code> representing <code>theValue</code> of
     * <code>this IntValue</code>.
     * 
     * @return a <code>DecimalValue</code> representing approximatly the same
     *          value as <code>this IntValue</code>
     */
    protected DecimalValue castDecimal() {
        if (value == null) {
            return new DecimalValue(null);
        }
        return new DecimalValue((double) value);
    }
    
    /**
     * Obtain a <code>MoneyValue</code> representing <code>theValue</code>
     * of <code>this IntValue</code>.
     * 
     * @return a <code>MoneyValue</code> representing the same value as
     *          <code>this IntValue</code>
     */
    protected MoneyValue castMoney() {
        if (value == null) {
            return new MoneyValue(null);
        }
        return new MoneyValue(value.toString());
    }
    
    /**
     * Compares <code>this IntValue</code> to another <code>Object</code>.
     * 
     * @param o the <code>Object</code> to compare to <code>this IntValue</code>
     * @return <code>true</code> if and only if <code>o</code> is a
     *          <code>IntValue</code> with the same <code>value</code> as
     *          <code>this IntValue</code> 
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        
        IntValue other = (IntValue) o;
        return value == null ? other.value == null : value.equals(other.value);
    }

    /**
     * @return an <code>int</code> containing a hash for <code>this IntValue</code> 
     */
    @Override
    public int hashCode() {
        return HASH_ORIGIN + Objects.hashCode(this.value);
    }
    
}
