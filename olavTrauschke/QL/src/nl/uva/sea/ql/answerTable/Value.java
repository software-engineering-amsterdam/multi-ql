package nl.uva.sea.ql.answerTable;

/**
 * Objects of this class represent values of QL
 * {@link nl.uva.sea.ql.ast.question.Question Question}s and
 * {@link nl.uva.sea.ql.ast.expr.Expr Expr}s.
 * 
 * @author Olav Trauschke
 * @version 6-mar-2016
 */
public abstract class Value {
    
    /**
     * Test whether <code>this Value</code> equals another according to ternary
     * logic.
     * 
     * @param other a <code>Value</code> to compare to this one
     * @return a <code>BooleanValue</code> representing <code>false</code> if
     *          <code>this Value</code> and <code>other</code> don't both
     *          represent a boolean value, both represent a numeric value or
     *          both represent a string value, an unknown value if this is not
     *          the case and <code>this Value</code> or <code>other</code>
     *          represents an unkonwn value and the result of comparing
     *          <code>this Value</code> to <code>other</code> otherwise
     */
    public abstract BooleanValue ternaryEquals(Value other);
    
    /**
     * Compares <code>this Value</code> to another <code>Object</code>.
     * Implemented to force subclasses to overwrite
     * {@link java.lang.Object#equals(java.lang.Object) Object.equals(Object)},
     * which should be done in a semantic way.
     * 
     * @param o the <code>Object</code> to compare to <code>this Value</code>
     * @return <code>true</code> if and only if <code>o</code> is equal to
     *          <code>this Value</code> 
     */
    @Override
    public abstract boolean equals(Object o);
    
    /**
     * Overwrites {@link java.lang.Object#hashCode() Object.hashCode()} to force
     * subclasses to overwrite it, to keep it consistent with <code>equals()</code>.
     * 
     * @return an <code>int</code> containing a hash for <code>this Value</code> 
     */
    @Override
    public abstract int hashCode();
    
}
