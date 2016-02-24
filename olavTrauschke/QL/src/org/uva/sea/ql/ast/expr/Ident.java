package org.uva.sea.ql.ast.expr;

/**
 * Representation of <code>Ident</code>s for questions in an AST.
 * 
 * @author Olav Trauschke, 10329463
 * @version 24-feb-2016
 */
public class Ident extends Expr {
    
    /**
     * Start value used to calculate hashes for objects of this class.
     */
    public static final int HASH_ORIGIN = 291;
    
    private final String content;
    
    /**
     * Constructor for <code>Ident</code>s.
     * 
     * @param theContent a <code>String</code> identifying the <code>Question</code>
     *                      with <code>this Ident</code>
     */
    public Ident(String theContent) {
        assert theContent != null;
        content = theContent;
    }
    
    /**
     * Compares <code>this Ident</code> to another <code>Object</code>. An
     * <code>Ident</code> is considered equal only to other objects of this
     * class for which <code>theContent</code> is equal to its own value for
     * this field.
     * 
     * @param o the <code>Object</code> to compare to <code>this Label</code>
     * @return <code>true</code> if and only if o is equal to <code>this Label</code> 
     */
    @Override
    public boolean equals(Object o) {
        return o != null
                && getClass() == o.getClass()
                && content.equals(((Ident) o).content);
    }
    
    /**
     * @return an <code>int</code> containing a hash for <code>this Ident</code>
     */
    @Override
    public int hashCode() {
        return HASH_ORIGIN + content.hashCode();
    }
    
}
