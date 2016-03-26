package nl.uva.sea.ql.answerTable;

import java.util.Objects;

/**
 * Objects of this class represent values of QL
 * {@link nl.uva.sea.ql.ast.question.StringQuestion StringQuestion}s and
 * {@link nl.uva.sea.ql.ast.expr.Expr Expr}s with a string value.
 * 
 * @author Olav Trauschke
 * @version 26-mar-2016
 */
public class StringValue extends Value {
    
    /**
     * Start value used to calculate hashes for objects of this class.
     */
    public static final int HASH_ORIGIN = 485;
    
    private final String value;
    
    /**
     * Constructor for objects of this class.
     * 
     * @param theValue a string value or <code>null</code> to represent an
     *                  unknown value
     */
    public StringValue(String theValue) {
        value = theValue;
    }
    
    /**
     * Test whether <code>this StringValue</code> equals another <code>Value</code>
     * according to ternary logic.
     * 
     * @param o a <code>Value</code> to compare to this one
     * @return a <code>BooleanValue</code> representing <code>false</code> if
     *          <code>other</code> is not a <code>StringValue</code>, an unknown
     *          value if this is not the case and <code>this StringValue</code>
     *          or <code>other</code> represents an unkonwn value and the result
     *          of comparing <code>theValue</code> of <code>this StringValue</code>
     *          to <code>theValue</code> of <code>other</code> otherwise
     */
    @Override
    public BooleanValue ternaryEquals(Value o) {
        if (o instanceof StringValue) {
            StringValue other = (StringValue) o;
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
     * Concatenate another <code>StringValue</code> to this one.
     * 
     * @param other a <code>StringValue</code> to concatenate to this one
     * @return a <code>StringValue</code> representing an unkonwn value if
     *          <code>this StringValue</code> or <code>other</code> represents
     *          an uknown value and the result of concatenating <code>other</code>
     *          to <code>this StringValue</code> otherwise
     */
    public StringValue concat(StringValue other) {
        if (value == null || other.value == null) {
            return new StringValue(null);
        }
        String result = value + other.value;
        return new StringValue(result);
    }
    
    /**
     * Compares <code>this StringValue</code> to another <code>Object</code>.
     * 
     * @param o the <code>Object</code> to compare to <code>this StringValue</code>
     * @return <code>true</code> if and only if <code>o</code> is a
     *          <code>StringValue</code> with the same <code>value</code> as
     *          <code>this StringValue</code> 
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        
        StringValue other = (StringValue) o;
        return value == null ? other.value == null : value.equals(other.value);
    }

    /**
     * @return an <code>int</code> containing a hash for <code>this StringValue</code> 
     */
    @Override
    public int hashCode() {
        return HASH_ORIGIN + Objects.hashCode(this.value);
    }
    
}
