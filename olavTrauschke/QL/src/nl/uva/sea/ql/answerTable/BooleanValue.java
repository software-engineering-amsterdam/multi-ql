package nl.uva.sea.ql.answerTable;

import java.util.Objects;

/**
 * Objects of this class represent values of QL
 * {@link nl.uva.sea.ql.ast.question.BooleanQuestion BooleanQuestion}s and
 * {@link nl.uva.sea.ql.ast.expr.Expr Expr}s with a boolean value.
 * 
 * @author Olav Trauschke
 * @version 26-mar-2016
 */
public class BooleanValue extends Value {
    
    /**
     * Start value used to calculate hashes for objects of this class.
     */
    public static final int HASH_ORIGIN = 95;
    
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
     * Cast a <code>Value</code> that is certain to be either boolean or unknown
     * to a <code>BooleanValue</code>.
     * 
     * @param toCast a <code>Value</code> to cast to a <code>BooleanValue</code>
     * @return <code>toCast</code> as a <code>BooleanValue</code> or a new
     *          <code>BooleanValue</code> representing an unknown value if
     *          <code>toCast</code> equals a (new) <code>UnknownValue</code>
     */
    public static BooleanValue cast(Value toCast) {
        if (toCast.equals(new UnknownValue())) {
            return new BooleanValue(null);
        }
        else {
            return (BooleanValue) toCast;
        }
    }
    
    /**
     * @return a <code>boolean</code> with the <code>value this BooleanValue</code>
     *          represents
     */
    public Boolean getValue() {
        return value;
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
        boolean valueFalse = value == null ? false : !value;
        boolean otherValueFalse = other.value == null ? false : !other.value;
        if (valueFalse || otherValueFalse) {
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
        boolean valueTrue = value == null ? false : value;
        boolean otherValueTrue = other.value == null ? false : other.value;
        if (valueTrue || otherValueTrue) {
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
    
    /**
     * Test whether <code>this BooleanValue</code> equals another <code>Value</code>
     * according to ternary logic.
     * 
     * @param o a <code>Value</code> to compare to this one
     * @return a <code>BooleanValue</code> representing <code>false</code> if
     *          <code>other</code> is not a <code>BooleanValue</code>, an unknown
     *          value if this is not the case and <code>this BooleanValue</code>
     *          or <code>other</code> represents an unkonwn value and the result
     *          of comparing <code>theValue</code> of <code>this BooleanValue</code>
     *          to <code>theValue</code> of <code>other</code> otherwise
     */
    @Override
    public BooleanValue ternaryEquals(Value o) {
        if (o instanceof BooleanValue) {
            BooleanValue other = (BooleanValue) o;
            if (value == null || other.value == null) {
                return new BooleanValue(null);
            }
            else {
                boolean equalValues = value.equals(other.value);
                return new BooleanValue(equalValues);
            }
        }
        else {
            return new BooleanValue(false);
        }
    }
    
    /**
     * Compares <code>this BooleanValue</code> to another <code>Object</code>.
     * 
     * @param o the <code>Object</code> to compare to <code>this BooleanValue</code>
     * @return <code>true</code> if and only if <code>o</code> is a
     *          <code>BooleanValue</code> with the same <code>value</code> as
     *          <code>this BooleanValue</code> 
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        
        BooleanValue other = (BooleanValue) o;
        return value == null ? other.value == null : value.equals(other.value);
    }
    
    /**
     * @return an <code>int</code> containing a hash for <code>this BooleanValue</code> 
     */
    @Override
    public int hashCode() {
        return HASH_ORIGIN + Objects.hashCode(this.value);
    }
    
}
