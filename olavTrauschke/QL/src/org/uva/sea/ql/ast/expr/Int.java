package org.uva.sea.ql.ast.expr;

import java.util.Objects;

/**
 * Representation of (literals of) the type int in an AST.
 * 
 * @author Olav Trauschke, 10329463
 * @version 24-feb-2016
 */
public class Int extends NumericExpr {
    
    /**
     * Start value used to calculate hashes for objects of this class.
     */
    public static final int HASH_ORIGIN = 259;
    
    private Integer value;
    
    /**
     * Constructor for objects of class <code>Int</code>.
     * 
     * @param theValue an <code>Integer</code> representing the value of the
     *                  constructed <code>Int</code>, or <code>null</code> if
     *                  it represents the return value of a <code>Question</code>
     *                  (which value is not yet known)
     */
    public Int(Integer theValue) {
        value = theValue;
    }
    
     /**
     * Compares <code>this Int</code> to another <code>Object</code>. An
     * <code>Int</code> is considered equal only to other objects of this class,
     * for which <code>theValue</code> is equal to its own value for this field.
     * 
     * @param o the <code>Object</code> to compare to <code>this Int</code>
     * @return <code>true</code> if and only if o is equal to <code>this Int</code> 
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        
        Int other = (Int) o;
        return value == null ? other.value == null : value.equals(other.value);
    }
    
    /**
     * @return an <code>int</code> containing a hash for <code>this Int</code>
     */
    @Override
    public int hashCode() {
        return HASH_ORIGIN + Objects.hashCode(this.value);
    }
}
