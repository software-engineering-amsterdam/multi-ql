package org.uva.sea.ql.ast.expr;

import java.util.Objects;
import org.uva.sea.ql.checker.ASTVisitor;

/**
 * Representation of (literals of) the type boolean in an AST.
 * 
 * @author Olav Trauschke, 10329463
 * @version 25-feb-2016
 */
public class Bool extends BooleanExpr {
    
    /**
     * Start value used to calculate hashes for objects of this class.
     */
    public static final int HASH_ORIGIN = 623;
    
    private Boolean value;
    
    /**
     * Constructor for objects of class <code>Bool</code>.
     * 
     * @param theValue a <code>Boolean</code> representing the value of the
     *                  constructed <code>Bool</code>, or <code>null</code> if
     *                  it represents the return value of a
     *                  <code>Question</code> (which value is not yet known)
     */
    public Bool(Boolean theValue) {
        value = theValue;
    }
    
    /**
     * Has <code>v visit this Bool</code>.
     * 
     * @param v an <code>ASTVisitor</code> that should <code>visit this Bool</code>
     */
    @Override
    public void accept(ASTVisitor v) {
        v.visit(this);
    }
    
    /**
     * Compares <code>this Bool</code> to another <code>Object</code>. A
     * <code>Bool</code> is considered equal only to other objects of this class,
     * for which <code>theValue</code> is equal to its own value for this field.
     * 
     * @param o the <code>Object</code> to compare to <code>this Bool</code>
     * @return <code>true</code> if and only if o is equal to <code>this Bool</code> 
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        
        Bool other = (Bool) o;
        if (value == null) return other.value == null;
        else return value.equals(other.value);
    }
    
    /**
     * @return an <code>int</code> containing a hash for <code>this Bool</code>
     */
    @Override
    public int hashCode() {
        return HASH_ORIGIN + Objects.hashCode(this.value);
    }
    
}
