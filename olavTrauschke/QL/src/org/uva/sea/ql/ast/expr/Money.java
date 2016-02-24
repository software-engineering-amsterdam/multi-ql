package org.uva.sea.ql.ast.expr;

import java.util.Objects;

/**
 * Representation of (literals of) the type money in an AST.
 * 
 * @author Olav Trauschke, 10329463
 * @version 24-feb-2016
 */
public class Money extends Expr {
    
    /**
     * Start value used to calculate hashes for objects of this class.
     */
    public static final int HASH_ORIGIN = 3;
    
    /**
     * Factor partial hashes are multiplied by to generate a hash for objects of this class.
     */
    public static final int HASH_FACTOR = 83;
    
    private Long units;
    private Byte cents;
    
    /**
     * Constructor for objects of class <code>Money</code>.
     * 
     * @param theUnits a <code>Long</code> representing the whole units of the
     *                  value of the constructed <code>Money</code> or
     *                  <code>null</code> if it represents the return value of a
     *                  <code>Question</code> (which value is not yet known)
     * @param theCents a <code>Byte</code> representing the whole units of the
     *                  value of the constructed <code>Money</code> (that should
     *                  be &gt;=0 and &lt;100.
     */
    public Money(Long theUnits, Byte theCents) {
        assert theCents == null || (theCents >= 0 && theCents < 100);
        units = theUnits;
        cents = theCents;
    }
    
    /**
     * Compares <code>this Money</code> to another <code>Object</code>. A
     * <code>Money</code> is considered equal only to other objects of this class,
     * for which <code>theUnits</code> and <code>theCents</code> are equal to
     * its own value for this field.
     * 
     * @param o the <code>Object</code> to compare to <code>this Money</code>
     * @return <code>true</code> if and only if o is equal to <code>this Money</code> 
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        
        Money other = (Money) o;
        boolean unitsEqual = units == null ? other.units == null : units.equals(other.units);
        boolean centsEqual = cents == null ? other.cents == null : cents.equals(other.cents);
        return unitsEqual && centsEqual;
    }
    
    /**
     * @return an <code>int</code> containing a hash for <code>this Money</code>
     */
    @Override
    public int hashCode() {
        int hash = HASH_ORIGIN;
        hash = HASH_FACTOR * hash + Objects.hashCode(this.units);
        hash = HASH_FACTOR * hash + Objects.hashCode(this.cents);
        return hash;
    }
    
}
