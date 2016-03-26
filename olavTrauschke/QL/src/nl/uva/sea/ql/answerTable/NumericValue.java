package nl.uva.sea.ql.answerTable;

/**
 * Objects of this class represent values of QL
 * {@link nl.uva.sea.ql.ast.question.Question Question}s and
 * {@link nl.uva.sea.ql.ast.expr.Expr Expr}s with a numeric (integer, money or
 * decimal) value.
 * 
 * @author Olav Trauschke
 * @version 26-mar-2016
 */
public abstract class NumericValue extends Value {
    
    /**
     * Cast a <code>Value</code> that is certain to be either numeric or unknown
     * to a <code>NumericValue</code>.
     * 
     * @param toCast a <code>Value</code> to cast to a <code>NumericValue</code>
     * @return <code>toCast</code> as a <code>NumericValue</code> or a new
     *          <code>UnknownNumericValue</code> if <code>toCast</code> equals a
     *          (new) <code>UnknownValue</code>
     */
    public static final NumericValue cast(Value toCast) {
        if (toCast.equals(new UnknownValue())) {
            return new UnknownNumericValue();
        }
        else {
            return (NumericValue) toCast;
        }
    }
    
    /**
     * Test whether <code>this NumericValue</code> equals another <code>Value</code>
     * according to ternary logic.
     * 
     * @param o a <code>Value</code> to compare to this one
     * @return a <code>BooleanValue</code> representing <code>false</code> if
     *          <code>other</code> is not a <code>NumericValue</code>, an unknown
     *          value if this is not the case and <code>this NumericValue</code>
     *          or <code>other</code> represents an unkonwn value and the result
     *          of comparing the value <code>this BooleanValue</code> represents
     *          to the value <code>other</code> represents otherwise
     */
    @Override
    public BooleanValue ternaryEquals(Value o) {
        if (o instanceof NumericValue) {
            NumericValue other = (NumericValue) o;
            return hasEqualValue(other);
        }
        else {
            return new BooleanValue(false);
        }
    }
    
    /**
     * Test whether <code>this NumericValue</code> equals another according to
     * ternary logic. Dispatches to a more specific case.
     * 
     * @param other a <code>NumericValue</code> to compare to this one
     * @return a <code>BooleanValue</code> representing an unknown value if
     *          <code>this NumericValue</code> or <code>other</code> represents
     *          an unknown value, <code>true</code> if this is not the case and
     *          <code>this NumericValue</code> and <code>other</code> represent
     *          equal values and <code>false</code> otherwise
     */
    protected abstract BooleanValue hasEqualValue(NumericValue other);
    
    /**
     * Test whether <code>this NumericValue</code> equals a specified
     * <code>DecimalValue</code> according to ternary logic.
     * 
     * @param other a <code>DecimalValue</code> to compare to
     *              <code>this NumericValue</code>
     * @return a <code>BooleanValue</code> representing an unknown value if
     *          <code>this NumericValue</code> or <code>other</code> represents
     *          an unkonwn value, <code>true</code> if this is not the case
     *          and <code>this NumericValue</code> and <code>other</code>
     *          represent equal values and <code>false</code> otherwise
     */
    protected abstract BooleanValue hasEqualValue(DecimalValue other);
    
    /**
     * Test whether <code>this NumericValue</code> equals a specified
     * <code>DecimalValue</code> according to ternary logic.
     * 
     * @param other a <code>DecimalValue</code> to compare to
     *              <code>this NumericValue</code>
     * @return a <code>BooleanValue</code> representing an unknown value if
     *          <code>this NumericValue</code> or <code>other</code> represents
     *          an unkonwn value, <code>true</code> if this is not the case
     *          and <code>this NumericValue</code> and <code>other</code>
     *          represent equal values and <code>false</code> otherwise
     */
    protected abstract BooleanValue hasEqualValue(IntValue other);
    
    /**
     * Test whether <code>this NumericValue</code> equals a specified
     * <code>DecimalValue</code> according to ternary logic.
     * 
     * @param other a <code>DecimalValue</code> to compare to
     *              <code>this NumericValue</code>
     * @return a <code>BooleanValue</code> representing an unknown value if
     *          <code>this NumericValue</code> or <code>other</code> represents
     *          an unkonwn value, <code>true</code> if this is not the case
     *          and <code>this NumericValue</code> and <code>other</code>
     *          represent equal values and <code>false</code> otherwise
     */
    protected abstract BooleanValue hasEqualValue(MoneyValue other);
    
    /**
     * Test whether <code>this NumericValue</code> is greater than another
     * according to ternary logic. Dispatches to a more specific case.
     * 
     * @param other a <code>NumericValue</code> to compare to this one
     * @return a <code>BooleanValue</code> representing an unknown value if
     *          <code>this NumericValue</code> or <code>other</code> represents
     *          an unknown value, <code>true</code> if this is not the case and
     *          <code>this NumericValue</code> is greater than <code>other</code>
     *          and <code>false</code> otherwise
     */
    public abstract BooleanValue ternaryGreaterThan(NumericValue other);
    
    /**
     * Test whether <code>this NumericValue</code> is greater than a specified
     * <code>DecimalValue</code> according to ternary logic.
     * 
     * @param other a <code>DecimalValue</code> to compare to
     *              <code>this NumericValue</code>
     * @return a <code>BooleanValue</code> representing an unknown value if
     *          <code>this NumericValue</code> or <code>other</code> represents
     *          an unkonwn value, <code>true</code> if this is not the case and
     *          <code>this NumericValue</code> is greater than <code>other</code>
     *          and <code>false</code> otherwise
     */
    protected abstract BooleanValue ternaryGreaterThan(DecimalValue other);
    
    /**
     * Test whether <code>this NumericValue</code> is greater than a specified
     * <code>IntValue</code> according to ternary logic.
     * 
     * @param other an <code>IntValue</code> to compare to
     *              <code>this NumericValue</code>
     * @return a <code>BooleanValue</code> representing an unknown value if
     *          <code>this NumericValue</code> or <code>other</code> represents
     *          an unkonwn value, <code>true</code> if this is not the case and
     *          <code>this NumericValue</code> is greater than <code>other</code>
     *          and <code>false</code> otherwise
     */
    protected abstract BooleanValue ternaryGreaterThan(IntValue other);
    
    /**
     * Test whether <code>this NumericValue</code> is greater than a specified
     * <code>MoneyValue</code> according to ternary logic.
     * 
     * @param other a <code>MoneyValue</code> to compare to
     *              <code>this NumericValue</code>
     * @return a <code>BooleanValue</code> representing an unknown value if
     *          <code>this NumericValue</code> or <code>other</code> represents
     *          an unkonwn value, <code>true</code> if this is not the case and
     *          <code>this NumericValue</code> is greater than <code>other</code>
     *          and <code>false</code> otherwise
     */
    protected abstract BooleanValue ternaryGreaterThan(MoneyValue other);
    
    /**
     * Add a specified <code>NumericValue</code> to <code>this NumericValue</code>.
     * Dispatches to a more specific case.
     * 
     * @param other a <code>NumericValue</code> to add to this one
     * @return a <code>NumericValue</code> representing an unkonwn value if
     *          <code>this NumericValue</code> or <code>other</code> represents
     *          an unknown value or the result of adding <code>other</code> to
     *          <code>this NumericValue</code> otherwise
     */
    public abstract NumericValue add(NumericValue other);
    
    /**
     * Add a specified <code>DecimalValue</code> to <code>this NumericValue</code>.
     * 
     * @param other a <code>DecimalValue</code> to add to
     *              <code>this NumericValue</code>
     * @return a <code>NumericValue</code> representing an unkonwn value if
     *          <code>this NumericValue</code> or <code>other</code> represents
     *          an unknown value or the result of adding <code>other</code> to
     *          <code>this NumericValue</code> otherwise
     */
    protected abstract NumericValue add(DecimalValue other);
    
    /**
     * Add a specified <code>IntValue</code> to <code>this NumericValue</code>.
     * 
     * @param other an <code>IntValue</code> to add to
     *              <code>this NumericValue</code>
     * @return a <code>NumericValue</code> representing an unkonwn value if
     *          <code>this NumericValue</code> or <code>other</code> represents
     *          an unknown value or the result of adding <code>other</code> to
     *          <code>this NumericValue</code> otherwise
     */
    protected abstract NumericValue add(IntValue other);
    
    /**
     * Add a specified <code>MoneyValue</code> to <code>this NumericValue</code>.
     * 
     * @param other a <code>MoneyValue</code> to add to
     *              <code>this NumericValue</code>
     * @return a <code>NumericValue</code> representing an unkonwn value if
     *          <code>this NumericValue</code> or <code>other</code> represents
     *          an unknown value or the result of adding <code>other</code> to
     *          <code>this NumericValue</code> otherwise
     */
    protected abstract MoneyValue add(MoneyValue other);
    
    /**
     * Multiply <code>this NumericValue</code> by another. Dispatches to a more
     * specific case.
     * 
     * @param other a <code>NumericValue</code> to multiply this one by
     * @return a <code>NumericValue</code> representing an unknown value if
     *          <code>this NumericValue</code> or <code>other</code> represents
     *          an unknown value or the result of multiplying these
     *          <code>Value</code>s otherwise
     */
    public abstract NumericValue multiply(NumericValue other);
    
    /**
     * Multiply <code>this NumericValue</code> by a specified
     * <code>DecimalValue</code>.
     * 
     * @param other a <code>DecimalValue</code> to multiply
     *              <code>this NumericValue</code> by
     * @return a <code>NumericValue</code> representing an unkonwn value if
     *          <code>this NumericValue</code> or <code>other</code> represents
     *          an unknown value or the result of multiplying these
     *          <code>Value</code>s otherwise
     */
    protected abstract NumericValue multiply(DecimalValue other);
    
    /**
     * Multiply <code>this NumericValue</code> by a specified
     * <code>IntValue</code>.
     * 
     * @param other an <code>IntValue</code> to multiply
     *              <code>this NumericValue</code> by
     * @return a <code>NumericValue</code> representing an unkonwn value if
     *          <code>this NumericValue</code> or <code>other</code> represents
     *          an unknown value or the result of multiplying these
     *          <code>Value</code>s otherwise
     */
    protected abstract NumericValue multiply(IntValue other);
    
    /**
     * Multiply <code>this NumericValue</code> by a specified
     * <code>MoneyValue</code>.
     * 
     * @param other a <code>MoneyValue</code> to multiply
     *              <code>this NumericValue</code> by
     * @return a <code>NumericValue</code> representing an unkonwn value if
     *          <code>this NumericValue</code> or <code>other</code> represents
     *          an unknown value or the result of multiplying these
     *          <code>Value</code>s otherwise
     */
    protected abstract MoneyValue multiply(MoneyValue other);
    
    /**
     * Divide <code>this NumericValue</code> by another. Dispatches to a more
     * specific case.
     * 
     * @param other a <code>NumericValue</code> to divide this one by
     * @return a <code>NumericValue</code> representing an unknown value if
     *          <code>this NumericValue</code> or <code>other</code> represents
     *          an unknown value or the result of dividing
     *          <code>this NumericValue</code> by <code>other</code> otherwise
     */
    public abstract NumericValue divide(NumericValue other);
    
    /**
     * Divide a specified <code>DecimalValue</code> by <code>this NumericValue</code>.
     * 
     * @param other a <code>DecimalValue</code> to divide by
     *              <code>this NumericValue</code>
     * @return a <code>NumericValue</code> representing an unknown value if
     *          <code>this NumericValue</code> or <code>other</code> represents
     *          an unknown value or the result of dividing
     *          <code>other</code> by <code>this NumericValue</code> otherwise
     */
    protected abstract DecimalValue inverseDivide(DecimalValue other);
    
    /**
     * Divide a specified <code>IntValue</code> by <code>this NumericValue</code>.
     * 
     * @param other a <code>DecimalValue</code> to divide by
     *              <code>this NumericValue</code>
     * @return a <code>NumericValue</code> representing an unknown value if
     *          <code>this NumericValue</code> or <code>other</code> represents
     *          an unknown value or the result of dividing
     *          <code>other</code> by <code>this NumericValue</code> otherwise
     */
    protected abstract DecimalValue inverseDivide(IntValue other);
    
    /**
     * Divide a specified <code>MoneyValue</code> by <code>this NumericValue</code>.
     * 
     * @param other a <code>MoneyValue</code> to divide by
     *              <code>this NumericValue</code>
     * @return a <code>NumericValue</code> representing an unknown value if
     *          <code>this NumericValue</code> or <code>other</code> represents
     *          an unknown value or the result of dividing
     *          <code>other</code> by <code>this NumericValue</code> otherwise
     */
    protected abstract NumericValue inverseDivide(MoneyValue other);
    
}
