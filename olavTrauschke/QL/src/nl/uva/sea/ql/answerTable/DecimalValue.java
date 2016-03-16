package nl.uva.sea.ql.answerTable;

/**
 * Objects of this class represent values of QL
 * {@link nl.uva.sea.ql.ast.question.DecimalQuestion DecimalQuestion}s.
 * 
 * @author Olav Trauschke
 * @version 16-mar-2016
 */
public class DecimalValue extends NumericValue {
    
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
     * Test whether <code>this DecimalValue</code> equals another
     * <code>NumericValue</code> according to ternary logic. Dispatches to a
     * more specific method of <code>other</code>.
     * 
     * @param other a <code>NumericValue</code> to compare to this one
     * @return a <code>BooleanValue</code> representing an unknown value if
     *          <code>this DecimalValue</code> or <code>other</code> represents
     *          an unkown value, <code>true</code> if this is not the case and
     *          <code>this DecimalValue</code> and <code>other</code> represent
     *          equal values and <code>false</code> otherwise
     */
    @Override
    public BooleanValue hasEqualValue(NumericValue other) {
        return other.hasEqualValue(this); //double dispatch to a more specific case
    }
    
    /**
     * Test whether <code>this DecimalValue</code> equals another according to
     * ternary logic.
     * 
     * @param other a <code>DecimalValue</code> to compare to <code>this one</code>
     * @return a <code>BooleanValue</code> representing an unknown value if
     *          <code>this DecimalValue</code> or <code>other</code> represents
     *          an unkown value, <code>true</code> if this is not the case and
     *          <code>this DecimalValue</code> and <code>other</code> represent
     *          equal values and <code>false</code> otherwise
     */
    @Override
    public BooleanValue hasEqualValue(DecimalValue other) {
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
     *          an unkown value, <code>true</code> if this is not the case and
     *          <code>this DecimalValue</code> and <code>other</code> represent
     *          equal values and <code>false</code> otherwise
     */
    @Override
    public BooleanValue hasEqualValue(IntValue other) {
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
     *          an unkown value, <code>true</code> if this is not the case and
     *          <code>this DecimalValue</code> and <code>other</code> represent
     *          equal values and <code>false</code> otherwise
     */
    @Override
    public BooleanValue hasEqualValue(MoneyValue other) {
        return other.hasEqualValue(this);
    }
    
    /**
     * Obtain a <code>MoneyValue</code> representing <code>theValue</code>
     * of <code>this DecimalValue</code>.
     * 
     * @return a <code>MoneyValue</code> representing the same value as
     *          <code>this DecimalValue</code>
     */
    public MoneyValue castMoney() {
        if (value == null) {
            return new MoneyValue(null);
        }
        return new MoneyValue(value);
    }
}
