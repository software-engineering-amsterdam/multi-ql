package org.uva.sea.ql.ast;

/**
 * Representation of <code>Date</code>s in an AST.
 * 
 * @author Olav Trauschke, 10329463
 * @version 24-feb-2016
 */
public class Date extends ASTNode {
    
    /**
     * Start value used to calculate hashes for objects of this class.
     */
    public static final int HASH_ORIGIN = 3;
    
    /**
     * Compares <code>this Date</code> to another <code>Object</code>. A
     * <code>Date</code> is considered equal only to other objects of this
     * class.
     * 
     * @param o the <code>Object</code> to compare to <code>this Date</code>
     * @return <code>true</code> if and only if o is equal to <code>this Date</code>
     */
    @Override
    public boolean equals(Object o) {
        return o != null && getClass() == o.getClass();
    }

    /**
     * @return <code>HASH_ORIGIN</code>
     */
    @Override
    public int hashCode() {
        return HASH_ORIGIN;
    }
    
}
