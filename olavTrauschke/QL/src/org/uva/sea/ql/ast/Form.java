package org.uva.sea.ql.ast;

import org.uva.sea.ql.ast.expr.Ident;

/**
 * Representation of <code>Form</code>s in an AST.
 * 
 * @author Olav Trauschke, 10329463
 * @version 24-feb-2016
 */
public class Form extends ASTNode {
    
    /**
     * Start value used to calculate hashes for objects of this class.
     */
    public static final int HASH_ORIGIN = 3;
    
    /**
     * Factor partial hashes are multiplied by to generate a hash for objects of this class.
     */
    public static final int HASH_FACTOR = 41;
    
    private final Ident identifier;
    private final ASTNode questions;
    
    /**
     * Constructor for <code>Form</code>s.
     * 
     * @param theIdentifier an <code>Ident</code> used to identify <code>this Form</code>
     * @param theQuestions an <code>ASTNode</code> representing the statements in
     *                      <code>this Form</code>
     */
    public Form(Ident theIdentifier, ASTNode theQuestions) {
        assert theIdentifier != null && theQuestions != null;
        identifier = theIdentifier;
        questions = theQuestions;
    }
    
    /**
     * Compares <code>this Form</code> to another <code>Object</code>. A
     * <code>Form</code> is considered equal only to other objects of this
     * class for which <code>theIdentifier</code> and <code>theQuestions</code>
     * are equal to its own values for these fields.
     * 
     * @param o the <code>Object</code> to compare to <code>this Form</code>
     * @return <code>true</code> if and only if o is equal to <code>this Form</code> 
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        
        Form other = (Form) o;
        return identifier.equals(other.identifier) && questions.equals(other.questions);
    }
    
    /**
     * @return an <code>int</code> containing a hash for <code>this Form</code>
     */
    @Override
    public int hashCode() {
        int hash = HASH_ORIGIN;
        hash = HASH_FACTOR * hash + identifier.hashCode();
        hash = HASH_FACTOR * hash + questions.hashCode();
        return hash;
    }
    
}
