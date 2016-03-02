package nl.uva.sea.ql.ast.expr;

import nl.uva.sea.ql.checker.ASTVisitor;

/**
 * Representation of (literals of) the type string in an AST.
 * 
 * @author Olav Trauschke
 * @version 1-mrt-2016
 */
public class Str extends Expr {
    
    /**
     * Start value used to calculate hashes for objects of this class.
     */
    public static final int HASH_ORIGIN = 249;
    
    private final String value;
    
    /**
     * Constructor for objects of class <code>Str</code>.
     * 
     * @param theValue a <code>String</code> representing the value of the
     *                  constructed <code>Str</code>
     */
    public Str(String theValue) {
        assert theValue != null;
        value = theValue;
    }
    
    /**
     * Has <code>v visit this Str</code>.
     * 
     * @param v an <code>ASTVisitor</code> that should <code>visit this Str</code>
     */
    @Override
    public void accept(ASTVisitor v) {
        v.visit(this);
    }
    
    /**
     * Compares <code>this Str</code> to another <code>Object</code>. A
     * <code>Str</code> is considered equal only to other objects of this class,
     * for which <code>theValue</code> is equal to its own value for this field.
     * 
     * @param o the <code>Object</code> to compare to <code>this Int</code>
     * @return <code>true</code> if and only if o is equal to <code>this Str</code> 
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        
        return value.equals(((Str) o).value);
    }
    
    /**
     * @return an <code>int</code> containing a hash for <code>this Str</code>
     */
    @Override
    public int hashCode() {
        return HASH_ORIGIN + value.hashCode();
    }
    
    /**
     * @return <code>theValue</code> of <code>this Str</code> as a <code>String</code>
     */
    public String getValue() {
        return value;
    }
    
}
