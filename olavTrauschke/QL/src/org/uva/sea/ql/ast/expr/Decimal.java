package org.uva.sea.ql.ast.expr;

import org.uva.sea.ql.checker.ASTVisitor;

/**
 * Representation of (literals of) the type decimal in an AST.
 * 
 * @author Olav Trauschke
 * @version 1-mrt-2016
 */
public class Decimal extends NumericExpr {
    
    /**
     * Start value used to calculate hashes for objects of this class.
     */
    public static final int HASH_ORIGIN = 87;
    
    private final Double value;
    
    /**
     * Constructor for objects of class <code>Decimal</code>.
     * 
     * @param theValue a <code>Double</code> representing the value of the
     *                  constructed <code>Decimal</code>
     */
    public Decimal(Double theValue) {
        assert theValue != null;
        value = theValue;
    }
    
    /**
     * Has <code>v visit this Decimal</code>.
     * 
     * @param v an <code>ASTVisitor</code> that should
     *          <code>visit this Decimal</code>
     */
    @Override
    public void accept(ASTVisitor v) {
        v.visit(this);
    }
    
    /**
     * Compares <code>this Decimal</code> to another <code>Object</code>. A
     * <code>Decimal</code> is considered equal only to other objects of this class,
     * for which <code>theValue</code> is equal to its own value for this field.
     * 
     * @param o the <code>Object</code> to compare to <code>this Decimal</code>
     * @return <code>true</code> if and only if o is equal to <code>this Decimal</code> 
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        
        return value.equals(((Decimal) o).value);
    }
    
    /**
     * @return an <code>int</code> containing a hash for <code>this Decimal</code>
     */
    @Override
    public int hashCode() {
        return HASH_ORIGIN + value.hashCode();
    }
}
