package nl.uva.sea.ql.answerTable;

/**
 * Objects of this class represent values of QL
 * {@link nl.uva.sea.ql.ast.question.Question Question}s and
 * {@link nl.uva.sea.ql.ast.expr.Expr Expr}s of which the currect value and type
 * are unknown.
 * 
 * @author Olav Trauschke
 * @version 30-mar-2016
 */
public class UnknownValue extends Value {
    
    /**
     * Start value used to calculate hashes for objects of this class.
     */
    public static final int HASH_ORIGIN = 5;
    
    /**
     * Test whether <code>this UnkonwnValue</code> is equal to a specified
     * <code>Value</code>.
     * 
     * @param other a <code>Value</code> to compare to <code>this UnkonwnValue</code>
     * @return a <code>BooleanValue</code> representing an unknown value
     */
    @Override
    public BooleanValue ternaryEquals(Value other) {
        return new BooleanValue(null);
    }
    
    /**
     * Compares <code>this UnknownValue</code> to another <code>Object</code>.
     * An <code>UnknownValue</code> is considered equal only to other objects of
     * the same class (but to all of them).
     * 
     * @param o the <code>Object</code> to compare to <code>this UnknownValue</code>
     * @return <code>true</code> if and only if o is an
     *          <code>UnknownValue</code> 
     */
    @Override
    public boolean equals(Object o) {
        return o == null ? false : getClass() == o.getClass();
    }
    
    /**
     * @return an <code>int</code> containing a hash for an <code>UnknownValue</code>
     */
    @Override
    public int hashCode() {
        return HASH_ORIGIN;
    }
    
}
