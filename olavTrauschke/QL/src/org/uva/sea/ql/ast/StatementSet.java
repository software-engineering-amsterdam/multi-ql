package org.uva.sea.ql.ast;

import java.util.HashSet;
import java.util.Set;

/**
 * Representation of the contents of a block in an AST.
 * 
 * @author Olav Trauschke, 10329463
 * @version 24-feb-2016
 */
public class StatementSet extends ASTNode {
    
    /**
     * Start value used to calculate hashes for objects of this class.
     */
    public static final int HASH_ORIGIN = 119;
    
    private final Set<ASTNode> set;
    
    /**
     * Constructor for (empty) <code>StatementSet</code>s.
     */
    public StatementSet() {
        set = new HashSet<>();
    }
    
    /**
     * Adds an <code>ASTNode</code> to <code>this StatementSet</code>, if no
     * equal <code>ASTNode</code> is already in it.
     * 
     * @param n the <code>ASTNode</code> to add to <code>this StatementSet</code>
     * @return <code>this StatementSet</code>, unlike a <code>java.util.Set</code>
     */
    public ASTNode add(ASTNode n) {
        set.add(n);
        return this;
    }
    
    /**
     * Compares <code>this StatementSet</code> to another <code>Object</code>. A
     * <code>StatementSet</code> is considered equal only to other objects of
     * this class, containing exactly the same elements, as specified by
     * {@link java.util.AbstractSet#equals(java.lang.Object)}.
     * 
     * @param o the <code>Object</code> to compare to
     *          <code>this StatementSet</code>
     * @return <code>true</code> if and only if o is equal to
     *          <code>this StatementSet</code> 
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        
        StatementSet other = (StatementSet) o;
        return set == null ? other.set == null : set.equals(other.set);
    }
    
    /**
     * @return an <code>int</code> containing a hash for <code>this StatementSet</code>
     */
    @Override
    public int hashCode() {
        return HASH_ORIGIN + set.hashCode();
    }
}
