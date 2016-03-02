package nl.uva.sea.ql.ast.expr;

import nl.uva.sea.ql.checker.ASTVisitor;

/**
 * Representation of (literals of) the type int in an AST.
 * 
 * @author Olav Trauschke
 * @version 1-mrt-2016
 */
public class Int extends NumericExpr {
    
    /**
     * Start value used to calculate hashes for objects of this class.
     */
    public static final int HASH_ORIGIN = 259;
    
    private final Integer value;
    
    /**
     * Constructor for objects of class <code>Int</code>.
     * 
     * @param theValue an <code>Integer</code> representing the value of the
     *                  constructed <code>Int</code>
     */
    public Int(Integer theValue) {
        assert theValue != null;
        value = theValue;
    }
    
    /**
     * Has <code>v visit this Int</code>.
     * 
     * @param v an <code>ASTVisitor</code> that should <code>visit this Int</code>
     */
    @Override
    public void accept(ASTVisitor v) {
        v.visit(this);
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
        
        return value.equals(((Int) o).value);
    }
    
    /**
     * @return an <code>int</code> containing a hash for <code>this Int</code>
     */
    @Override
    public int hashCode() {
        return HASH_ORIGIN + value.hashCode();
    }
}
