package org.uva.sea.ql.ast;

import org.uva.sea.ql.ast.expr.*;
import org.uva.sea.ql.checker.ASTVisitor;

/**
 * Representation of <code>Question</code>s in an AST.
 * 
 * @author Olav Trauschke, 10329463
 * @version 25-feb-2016
 */
public class Question extends ASTNode {
    
    /**
     * Start value used to calculate hashes for objects of this class.
     */
    public static final int HASH_ORIGIN = 7;
    
    /**
     * Factor partial hashes are multiplied by to generate a hash for objects of this class.
     */
    public static final int HASH_FACTOR = 23;
    
    private final Ident identifier;
    private final Label label;
    private final ASTNode type;
    
    /**
     * Constructor for <code>Questions</code>s.
     * 
     * @param theIdentifier an <code>Ident</code> used to identify the constructed
     *                      <code>Question</code>
     * @param theLabel a <code>Label</code> to display with the constructed
     *                  <code>Question</code>
     * @param theType an <code>ASTNode</code> representing the type of the answer to
     *                  the constructed <code>Question</code>
     */
    public Question(Ident theIdentifier, Label theLabel, ASTNode theType) {
        assert theIdentifier != null && theLabel != null && theType != null;
        identifier = theIdentifier;
        label = theLabel;
        type = theType;
    }
    
    /**
     * Has the <code>identifier</code>, the <code>label</code> and the
     * <code>type</code> of <code>this Question accept v</code> and then has
     * <code>v visit this Question</code>.
     * 
     * @param v an <code>ASTVisitor</code> that should visit
     *          <code>this Question</code> and its children
     */
    @Override
    public void accept(ASTVisitor v) {
        identifier.accept(v);
        label.accept(v);
        type.accept(v);
        
        v.visit(this);
    }
    
    /**
     * @return an <code>Ident</code> identifying <code>this Question</code>
     */
    protected Ident getIdentifier() {
        return identifier;
    }
    
    /**
     * @return the <code>Label</code> to be displayed with <code>this Question</code>
     */
    protected Label getLabel() {
        return label;
    }
    
    /**
     * @return an <code>ASTNode</code> representing the returntype of
     *          <code>this Question</code>
     */
    protected ASTNode getType() {
        return type;
    }
    
    /**
     * Compares <code>this Question</code> to another <code>Object</code>. A
     * <code>Question</code> is considered equal only to other objects of this
     * class for which <code>theIdentifier</code>, <code>theLabel</code> and
     * <code>theType</code> are equal to its own values for these fields.
     * 
     * @param o the <code>Object</code> to compare to <code>this Question</code>
     * @return <code>true</code> if and only if o is equal to <code>this Question</code> 
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        
        Question other = (Question) o;
        return identifier.equals(other.identifier)
               && label.equals(other.label)
               && type.equals(other.type);
    }
    
    /**
     * @return an <code>int</code> containing a hash for <code>this Question</code>
     */
    @Override
    public int hashCode() {
        int hash = HASH_ORIGIN;
        hash = HASH_FACTOR * hash + identifier.hashCode();
        hash = HASH_FACTOR * hash + label.hashCode();
        hash = HASH_FACTOR * hash + type.hashCode();
        return hash;
    }
    
}
