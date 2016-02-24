package org.uva.sea.ql.ast.expr;

/**
 * Representation of a (boolean) negation in an AST.
 * 
 * @author Olav Trauschke
 * @version 24-feb-2016
 */
public class Not extends BooleanExpr {
    
    /**
     * Start value used to calculate hashes for objects of this class.
     */
    public static final int HASH_ORIGIN = 65;
    
    private final Expr content;
    
    /**
     * Constructor for objects of class <code>Not</code>.
     * 
     * @param theContent an <code>Integer</code> representing the expression
     *                      that is negated
     */
    public Not(Expr theContent) {
        assert theContent != null;
        content = theContent;
    }
    
    /**
     * Compares <code>this Not</code> to another <code>Object</code>. A
     * <code>Not</code> is considered equal only to other objects of this class,
     * for which <code>theContent</code> is equal to its own value for this field.
     * 
     * @param o the <code>Object</code> to compare to <code>this Int</code>
     * @return <code>true</code> if and only if o is equal to <code>this Int</code> 
     */
    @Override
    public boolean equals(Object o) {
        return o != null
                && getClass() == o.getClass()
                && content.equals(((Not) o).content);
    }
    
    /**
     * @return an <code>int</code> containing a hash for <code>this Not</code>
     */
    @Override
    public int hashCode() {
        return HASH_ORIGIN + content.hashCode();
    }
    
}
