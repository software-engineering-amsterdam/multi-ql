package nl.uva.sea.ql.answerTable;

import java.util.Date;
import java.util.Objects;

/**
 * Objects of this class represent values of QL
 * {@link nl.uva.sea.ql.ast.question.DateQuestion DateQuestion}s and
 * {@link nl.uva.sea.ql.ast.expr.Expr Expr}s with a boolean value.
 * 
 * @author Olav Trauschke
 * @version 30-mar-2016
 */
public class DateValue extends Value {
    
    /**
     * Start value used to calculate hashes for objects of this class.
     */
    public static final int HASH_ORIGIN = 497;
    
    private final Date value;
    
    /**
     * Constructor for objects of this class.
     * 
     * @param theValue a <code>Date</code> the constructed <code>DateValue</code>
     *                  should represent or <code>null</code> (for unknown)
     */
    public DateValue(Date theValue) {
        value = (Date) theValue.clone();
    }
    
    /**
     * @return a <code>Date</code> with the <code>value this DateValue</code>
     *          represents
     */
    public Date getValue() {
        return (Date) value.clone();
    }
    
    /**
     * Test whether <code>this DateValue</code> equals another <code>Value</code>
     * according to ternary logic.
     * 
     * @param o a <code>Value</code> to compare to this one
     * @return a <code>BooleanValue</code> representing <code>false</code> if
     *          <code>other</code> is not a <code>DateValue</code>, an unknown
     *          value if this is not the case and <code>this DateValue</code>
     *          or <code>other</code> represents an unkonwn value and the result
     *          of comparing <code>theValue</code> of <code>this DateValue</code>
     *          to <code>theValue</code> of <code>other</code> otherwise
     */
    @Override
    public BooleanValue ternaryEquals(Value o) {
        if (o instanceof DateValue) {
            DateValue other = (DateValue) o;
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
     * Compares <code>this DateValue</code> to another <code>Object</code>.
     * 
     * @param o the <code>Object</code> to compare to <code>this DateValue</code>
     * @return <code>true</code> if and only if <code>o</code> is a
     *          <code>DateValue</code> with the same <code>value</code> as
     *          <code>this BooleanValue</code> 
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        
        DateValue other = (DateValue) o;
        return value == null ? other.value == null : value.equals(other.value);
    }
    
    /**
     * @return an <code>int</code> containing a hash for <code>this DateValue</code> 
     */
    @Override
    public int hashCode() {
        return HASH_ORIGIN + Objects.hashCode(this.value);
    }
    
}
