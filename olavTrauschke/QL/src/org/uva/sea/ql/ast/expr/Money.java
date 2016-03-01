package org.uva.sea.ql.ast.expr;

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
    public static final int HASH_ORIGIN = 3;
    
    /**
     * Factor partial hashes are multiplied by to generate a hash for objects of this class.
     */
    public static final int HASH_FACTOR = 83;
    
    private final Long units;
    private final Byte cents;
    
    /**
     * Constructor for objects of class <code>Money</code>.
     * 
     * @param theUnits a <code>Long</code> representing the whole units of the
     *                  value of the constructed <code>Money</code>
     * @param theCents a <code>Byte</code> representing the whole units of the
     *                  value of the constructed <code>Money</code> (that should
     *                  be &gt;=0 and &lt;100.
     */
    public Money(Long theUnits, Byte theCents) {
        assert theUnits!= null && theCents != null;
        units = theUnits;
        cents = theCents;
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
        return units.equals(other.units) && cents.equals(other.cents);
    }
    
    /**
     * @return an <code>int</code> containing a hash for <code>this Money</code>
     */
    @Override
    public int hashCode() {
        int hash = HASH_ORIGIN;
        hash = HASH_FACTOR * hash + units.hashCode();
        hash = HASH_FACTOR * hash + cents.hashCode();
        return hash;
    }
    
}
