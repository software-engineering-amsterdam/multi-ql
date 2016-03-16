package nl.uva.sea.ql.answerTable;

/**
 * Objects of this class represent values of QL
 * {@link nl.uva.sea.ql.ast.question.BooleanQuestion BooleanQuestion}s and
 * {@link nl.uva.sea.ql.ast.expr.Expr Expr}s with a boolean value.
 * 
 * @author Olav Trauschke
 * @version 16-mrt-2016
 */
public class BooleanValue extends Value {
    
    private final Boolean value;
    
    /**
     * Constructor for objects of this class.
     * 
     * @param theValue <code>true</code>, <code>false</code> or <code>null</code>
     *                  (for unknown)
     */
    public BooleanValue(Boolean theValue) {
        value = theValue;
    }
    
    /**
     * Conjunct <code>this BooleanValue</code> to another.
     * 
     * @param other a <code>BooleanValue</code> to conjunct with
     *              <code>this BooleanValue</code>
     * @return a <code>BooleanValue</code> representing <code>false</code> if
     *          and only if <code>theValue</code> of <code>this BooleanValue</code>
     *          or <code>theValue</code> of <code>other</code> represents
     *          <code>false</code>, an unknown value if this is not the case and
     *          <code>theValue</code> of <code>this BooleanValue</code> or
     *          <code>theValue</code> of <code>other</code> is unknown and
     *          <code>true</code> otherwise
     */
    public BooleanValue conjunct(BooleanValue other) {
        if (value == false || other.value == false) {
            return new BooleanValue(false);
        }
        if (value == null || other.value == null) {
            return new BooleanValue(null);
        }
        return this; //no need to construct a new object equal to this
    }
    
    /**
     * Disjunct <code>this BooleanValue</code> to another.
     * 
     * @param other a <code>BooleanValue</code> to conjuct with
     *              <code>this BooleanValue</code>
     * @return a <code>BooleanValue</code> representing <code>true</code> if and
     *          only if <code>theValue</code> of <code>this BooleanValue</code>
     *          or <code>theValue</code> of <code>other</code> represents
     *          <code>true</code>. an unknown value if this is not the case and
     *          <code>theValue</code> of <code>this BooleanValue</code> or
     *          <code>theValue</code> of <code>other</code> is unknown and
     *          <code>false</code> otherwise
     */
    public BooleanValue disjunct(BooleanValue other) {
        if (value == true || other.value == true ) {
            return new BooleanValue(true);
        }
        if (value == null || other.value == null) {
            return new BooleanValue(null);
        }
        return this; // no need to construct a new object equal to this
    }
    
    /**
     * Negate <code>this BooleanValue</code>.
     * 
     * @return a <code>BooleanValue</code> representing an unknown value if and
     *          only if <code>this BooleanValue</code> represents an unknown
     *          value and the negation of <code>theValue</code> of
     *          <code>this BooleanValue</code> otherwise
     */
    public BooleanValue negate() {
        if (value == null) {
            return this;
        }
        return new BooleanValue(!value);
    }
    
}
