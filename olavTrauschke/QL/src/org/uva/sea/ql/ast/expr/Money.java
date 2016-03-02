package org.uva.sea.ql.ast.expr;

import java.math.BigDecimal;
import org.uva.sea.ql.checker.ASTVisitor;

/**
 * Representation of (literals of) the type money in an AST.
 * 
 * @author Olav Trauschke
 * @version 1-mar-2016
 */
public class Money extends NumericExpr {
    
    /**
     * Start value used to calculate hashes for objects of this class.
     */
    public static final int HASH_ORIGIN = 371;
    
    private final BigDecimal value;
    
    /**
     * Constructor for objects of class <code>Money</code>.
     * 
     * @param theValue a <code>String</code> in the format specified for
     *                  {@link java.math.BigDecimal#BigDecimal(java.lang.String) BigDecimal(String)}
     *                  representing the value for the <code>Money</code> to
     *                  construct
     */
    public Money(String theValue) {
        assert theValue != null;
        value = new BigDecimal(theValue);
    }
    
    /**
     * Has <code>v visit this Money</code>.
     * 
     * @param v an <code>ASTVisitor</code> that should <code>visit this Money</code>
     */
    @Override
    public void accept(ASTVisitor v) {
        v.visit(this);
    }
    
    /**
     * Compares <code>this Money</code> to another <code>Object</code>. A
     * <code>Money</code> is considered equal only to other objects of this class,
     * for which <code>theValue</code> is equal to its own value for this field.
     * 
     * @param o the <code>Object</code> to compare to <code>this Money</code>
     * @return <code>true</code> if and only if o is equal to <code>this Money</code> 
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        
        Money other = (Money) o;
        return value.equals(other.value);
    }
    
    /**
     * @return an <code>int</code> containing a hash for <code>this Money</code>
     */
    @Override
    public int hashCode() {
        return HASH_ORIGIN + value.hashCode();
    }
    
}
