package nl.uva.sea.ql.ast;

import java.util.LinkedHashSet;
import java.util.Set;
import nl.uva.sea.ql.ASTVisitor;

/**
 * Representation of the contents of a block in an AST.
 * 
 * @author Olav Trauschke
 * @version 10-mar-2016
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
        set = new LinkedHashSet<>();
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
     * Has all the <code>Statement</code>s in
     * <code>this StatementSet accept visitor</code> and then has
     * <code>visitor visit this StatementSet</code>. The <code>Statement</code>s are
     * visited in reverse order, to process them in the same order as they were
     * in a ql-file that was analyzed by a
     * {@link nl.uva.sea.ql.parser.ParserWrapper Parser(Wrapper)}
     * 
     * @param visitor an <code>ASTVisitor</code> that should
     *          <code>visit this StatementSet</code> and its children
     */
    @Override
    public void accept(ASTVisitor visitor) {
        ASTNode[] nodes = set.toArray(new ASTNode[0]);
        for (int i = nodes.length - 1; i >= 0; i--) {
            nodes[i].accept(visitor);
        }
        visitor.visit(this);
    }
    
    /**
     * Compares <code>this StatementSet</code> to another <code>Object</code>. A
     * <code>StatementSet</code> is considered equal only to other objects of
     * this class, containing exactly the same elements, as specified by
     * {@link java.util.AbstractSet#equals(java.lang.Object) AbstractSet.equals(Object)}.
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
