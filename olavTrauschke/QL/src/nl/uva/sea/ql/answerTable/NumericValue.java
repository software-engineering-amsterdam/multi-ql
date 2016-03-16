package nl.uva.sea.ql.answerTable;

/**
 * Objects of this class represent values of QL
 * {@link nl.uva.sea.ql.ast.question.Question Question}s and
 * {@link nl.uva.sea.ql.ast.expr.Expr Expr}s with a numeric (integer, money or
 * decimal) value.
 * 
 * @author Olav Trauschke
 * @version 16-mrt-2016
 */
public abstract class NumericValue extends Value {
    
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
     *          an unkown value, <code>true</code> if this is not the case and
     *          <code>this NumericValue</code> and <code>other</code> represent
     *          equal values and <code>false</code> otherwise
     */
    public abstract BooleanValue hasEqualValue(NumericValue other);
    
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
    public abstract BooleanValue hasEqualValue(DecimalValue other);
    
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
    public abstract BooleanValue hasEqualValue(IntValue other);
    
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
    public abstract BooleanValue hasEqualValue(MoneyValue other);
}
