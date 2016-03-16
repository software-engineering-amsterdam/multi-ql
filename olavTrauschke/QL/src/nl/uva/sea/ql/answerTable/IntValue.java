package nl.uva.sea.ql.answerTable;

/**
 * Objects of this class represent values of QL
 * {@link nl.uva.sea.ql.ast.question.IntQuestion IntQuestion}s and
 * {@link nl.uva.sea.ql.ast.expr.Expr Expr}s with an integer value.
 * 
 * @author Olav Trauschke
 * @version 16-mrt-2016
 */
public class IntValue extends NumericValue {
    
    private final Integer value;
    
    /**
     * Constructor for objects of this class.
     * 
     * @param theValue an integer value or <code>null</code> to represent an
     *                  unknown value
     */
    public IntValue(Integer theValue) {
        value = theValue;
    }
    
    /**
     * Test whether <code>this IntValue</code> equals another
     * <code>NumericValue</code> according to ternary logic. Dispatches to a
     * more specific method of <code>other</code>.
     * 
     * @param other a <code>NumericValue</code> to compare to this one
     * @return a <code>BooleanValue</code> representing an unknown value if
     *          <code>this IntValue</code> or <code>other</code> represents an
     *          unkown value, <code>true</code> if this is not the case and
     *          <code>this IntValue</code> and <code>other</code> represent equal
     *          values and <code>false</code> otherwise
     */
    @Override
    public BooleanValue hasEqualValue(NumericValue other) {
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
     *          unkown value, <code>true</code> if this is not the case and
     *          <code>this IntValue</code> and <code>other</code> represent equal
     *          values and <code>false</code> otherwise
     */
    @Override
    public BooleanValue hasEqualValue(DecimalValue other) {
        return other.hasEqualValue(this);
    }
    
    /**
     * Test whether <code>this IntValue</code> equals another according to
     * ternary logic.
     * 
     * @param other an <code>IntValue</code> to compare to <code>this one</code>
     * @return a <code>BooleanValue</code> representing an unknown value if
     *          <code>this IntValue</code> or <code>other</code> represents an
     *          unkown value, <code>true</code> if this is not the case and
     *          <code>this IntValue</code> and <code>other</code> represent equal
     *          values and <code>false</code> otherwise
     */
    @Override
    public BooleanValue hasEqualValue(IntValue other) {
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
     *          unkown value, <code>true</code> if this is not the case and
     *          <code>this IntValue</code> and <code>other</code> represent equal
     *          values and <code>false</code> otherwise
     */
    @Override
    public BooleanValue hasEqualValue(MoneyValue other) {
        return other.hasEqualValue(this);
    }
    
    /**
     * Obtain a <code>DecimalValue</code> representing the value closest to
     * <code>theValue</code> of <code>this IntValue</code> that can be
     * represented by a <code>DecimalValue</code>.
     * 
     * @return a <code>DecimalValue</code> representing approximatly the same
     *          value as <code>this IntValue</code>
     */
    public DecimalValue castDecimal() {
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
    public MoneyValue castMoney() {
        if (value == null) {
            return new MoneyValue(null);
        }
        return new MoneyValue(value.toString());
    }
    
}
