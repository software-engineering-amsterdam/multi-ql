package nl.uva.sea.ql.answerTable;

/**
 * Objects of this class represent values of QL
 * {@link nl.uva.sea.ql.ast.question.Question Question}s and
 * {@link nl.uva.sea.ql.ast.expr.Expr Expr}s with a numeric (integer, money or
 * decimal) value of which the currect value and type (integer, money or decimal)
 * are unknown.
 * 
 * @author Olav Trauschke
 * @version 26-mar-2016
 */
public class UnknownNumericValue extends NumericValue {
    
    /**
     * Start value used to calculate hashes for objects of this class.
     */
    public static final int HASH_ORIGIN = 3;
    
    /**
     * Test whether <code>this UnkonwnNumericValue</code> has a value equal to
     * a specified <code>NumericValue</code>.
     * 
     * @param other a <code>NumericValue</code> to compare to
     *              <code>this UnkonwnNumericValue</code>
     * @return a <code>BooleanValue</code> representing an unknown value
     */
    @Override
    protected BooleanValue hasEqualValue(NumericValue other) {
        return new BooleanValue(null);
    }
    
    /**
     * Test whether <code>this UnkonwnNumericValue</code> has a value equal to
     * a specified <code>DecimalValue</code>.
     * 
     * @param other a <code>DecimalValue</code> to compare to
     *              <code>this UnkonwnNumericValue</code>
     * @return a <code>BooleanValue</code> representing an unknown value
     */
    @Override
    protected BooleanValue hasEqualValue(DecimalValue other) {
        return new BooleanValue(null);
    }
    
    /**
     * Test whether <code>this UnkonwnNumericValue</code> has a value equal to
     * a specified <code>IntValue</code>.
     * 
     * @param other a <code>IntValue</code> to compare to
     *              <code>this UnkonwnNumericValue</code>
     * @return a <code>BooleanValue</code> representing an unknown value
     */
    @Override
    protected BooleanValue hasEqualValue(IntValue other) {
        return new BooleanValue(null);
    }
    
    /**
     * Test whether <code>this UnkonwnNumericValue</code> has a value equal to
     * a specified <code>MoneyValue</code>.
     * 
     * @param other a <code>MoneyValue</code> to compare to
     *              <code>this UnkonwnNumericValue</code>
     * @return a <code>BooleanValue</code> representing an unknown value
     */
    @Override
    protected BooleanValue hasEqualValue(MoneyValue other) {
        return new BooleanValue(null);
    }
    
    /**
     * Test whether <code>this UnkonwnNumericValue</code> has a value greater
     * than a specified <code>NumericValue</code>.
     * 
     * @param other a <code>NumericValue</code> to compare to
     *              <code>this UnkonwnNumericValue</code>
     * @return a <code>BooleanValue</code> representing an unknown value
     */
    @Override
    public BooleanValue ternaryGreaterThan(NumericValue other) {
        return new BooleanValue(null);
    }
    
    /**
     * Test whether <code>this UnkonwnNumericValue</code> has a value greater
     * than a specified <code>DecimalValue</code>.
     * 
     * @param other a <code>DecimalValue</code> to compare to
     *              <code>this UnkonwnNumericValue</code>
     * @return a <code>BooleanValue</code> representing an unknown value
     */
    @Override
    protected BooleanValue ternaryGreaterThan(DecimalValue other) {
        return new BooleanValue(null);
    }
    
    /**
     * Test whether <code>this UnkonwnNumericValue</code> has a value greater
     * than a specified <code>IntValue</code>.
     * 
     * @param other a <code>IntValue</code> to compare to
     *              <code>this UnkonwnNumericValue</code>
     * @return a <code>BooleanValue</code> representing an unknown value
     */
    @Override
    protected BooleanValue ternaryGreaterThan(IntValue other) {
        return new BooleanValue(null);
    }
    
    /**
     * Test whether <code>this UnkonwnNumericValue</code> has a value greater
     * than a specified <code>MoneyValue</code>.
     * 
     * @param other a <code>MoneyValue</code> to compare to
     *              <code>this UnkonwnNumericValue</code>
     * @return a <code>BooleanValue</code> representing an unknown value
     */
    @Override
    protected BooleanValue ternaryGreaterThan(MoneyValue other) {
        return new BooleanValue(null);
    }
    
    /**
     * Add <code>this UnkonwnNumericValue</code> to a specified
     * <code>NumericValue</code>.
     * 
     * @param other a <code>NumericValue</code> to add to
     *              <code>this UnkonwnNumericValue</code>
     * @return a new <code>UnknownNumericValue</code>
     */
    @Override
    public NumericValue add(NumericValue other) {
        return new UnknownNumericValue();
    }
    
    /**
     * Add <code>this UnkonwnNumericValue</code> to a specified
     * <code>DecimalValue</code>.
     * 
     * @param other a <code>DecimalValue</code> to add to
     *              <code>this UnkonwnNumericValue</code>
     * @return a new <code>UnknownNumericValue</code>
     */
    @Override
    protected NumericValue add(DecimalValue other) {
        return new UnknownNumericValue();
    }
    
    /**
     * Add <code>this UnkonwnNumericValue</code> to a specified
     * <code>IntValue</code>.
     * 
     * @param other a <code>IntValue</code> to add to
     *              <code>this UnkonwnNumericValue</code>
     * @return a new <code>UnknownNumericValue</code>
     */
    @Override
    protected NumericValue add(IntValue other) {
        return new UnknownNumericValue();
    }
    
    /**
     * Add <code>this UnkonwnNumericValue</code> to a specified
     * <code>MoneyValue</code>.
     * 
     * @param other a <code>MoneyValue</code> to add to
     *              <code>this UnkonwnNumericValue</code>
     * @return a new <code>UnknownNumericValue</code>
     */
    @Override
    protected MoneyValue add(MoneyValue other) {
        return new MoneyValue(null);
    }
    
    /**
     * Multiply <code>this UnkonwnNumericValue</code> by a specified
     * <code>NumericValue</code>.
     * 
     * @param other a <code>NumericValue</code> to multiply
     *              <code>this UnkonwnNumericValue</code> by
     * @return a new <code>UnknownNumericValue</code>
     */
    @Override
    public NumericValue multiply(NumericValue other) {
        return new UnknownNumericValue();
    }
    
    /**
     * Multiply <code>this UnkonwnNumericValue</code> by a specified
     * <code>DecimalValue</code>.
     * 
     * @param other a <code>DecimalValue</code> to multiply
     *              <code>this UnkonwnNumericValue</code> by
     * @return a new <code>UnknownNumericValue</code>
     */
    @Override
    protected NumericValue multiply(DecimalValue other) {
        return new UnknownNumericValue();
    }
    
    /**
     * Multiply <code>this UnkonwnNumericValue</code> by a specified
     * <code>IntValue</code>.
     * 
     * @param other an <code>IntValue</code> to multiply
     *              <code>this UnkonwnNumericValue</code> by
     * @return a new <code>UnknownNumericValue</code>
     */
    @Override
    protected NumericValue multiply(IntValue other) {
        return new UnknownNumericValue();
    }
    
    /**
     * Multiply <code>this UnkonwnNumericValue</code> by a specified
     * <code>MoneyValue</code>.
     * 
     * @param other a <code>MoneyValue</code> to multiply
     *              <code>this UnkonwnNumericValue</code> by
     * @return a new <code>UnknownNumericValue</code>
     */
    @Override
    protected MoneyValue multiply(MoneyValue other) {
        return new MoneyValue(null);
    }
    
    /**
     * Divide <code>this UnkonwnNumericValue</code> by a specified
     * <code>NumericValue</code>.
     * 
     * @param other a <code>NumericValue</code> to divide
     *              <code>this UnkonwnNumericValue</code> by
     * @return a new <code>UnknownNumericValue</code>
     */
    @Override
    public NumericValue divide(NumericValue other) {
        return new UnknownNumericValue();
    }
    
    /**
     * Divide a specified <code>DecimalValue</code> by
     * <code>this UnkonwnNumericValue</code>.
     * 
     * @param other a <code>DecimalValue</code> to divide by
     *              <code>this UnkonwnNumericValue</code>
     * @return a new <code>UnknownNumericValue</code>
     */
    @Override
    protected DecimalValue inverseDivide(DecimalValue other) {
        return new DecimalValue(null);
    }
    
    /**
     * Divide a specified <code>IntValue</code> by
     * <code>this UnkonwnNumericValue</code>.
     * 
     * @param other an <code>IntValue</code> to divide by
     *              <code>this UnkonwnNumericValue</code>
     * @return a new <code>UnknownNumericValue</code>
     */
    @Override
    protected DecimalValue inverseDivide(IntValue other) {
        return new DecimalValue(null);
    }
    
    /**
     * Divide a specified <code>MoneyValue</code> by
     * <code>this UnkonwnNumericValue</code>.
     * 
     * @param other a <code>MoneyValue</code> to divide by
     *              <code>this UnkonwnNumericValue</code>
     * @return a new <code>UnknownNumericValue</code>
     */
    @Override
    protected NumericValue inverseDivide(MoneyValue other) {
        return new UnknownNumericValue();
    }
    
    /**
     * Compares <code>this UnknownNumericValue</code> to another <code>Object</code>.
     * An <code>UnknownNumericValue</code> is considered equal only to other
     * objects of the same class (but to all of them).
     * 
     * @param o the <code>Object</code> to compare to
     *          <code>this UnknownNumericValue</code>
     * @return <code>true</code> if and only if o is an
     *          <code>UnknownNumericValue</code> 
     */
    @Override
    public boolean equals(Object o) {
        return getClass() == o.getClass();
    }
    
    /**
     * @return an <code>int</code> containing a hash for an
     *          <code>UnknownNumericValue</code>
     */
    @Override
    public int hashCode() {
        return HASH_ORIGIN;
    }
    
}
