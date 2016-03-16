package nl.uva.sea.ql.ast.expr;

import nl.uva.sea.ql.answerTable.AnswerTable;
import nl.uva.sea.ql.answerTable.BooleanValue;
import nl.uva.sea.ql.checker.ASTVisitor;

/**
 * Representation of (literals of) the type boolean in an AST.
 * 
 * @author Olav Trauschke
 * @version 16-mar-2016
 */
public class Bool extends BooleanExpr {
    
    /**
     * Start value used to calculate hashes for objects of this class.
     */
    public static final int HASH_ORIGIN = 623;
    
    private final Boolean value;
    
    /**
     * Constructor for objects of class <code>Bool</code>.
     * 
     * @param theValue a <code>Boolean</code> representing the value of the
     *                  constructed <code>Bool</code>
     */
    public Bool(Boolean theValue) {
        assert theValue != null;
        value = theValue;
    }
    
    /**
     * Has <code>visitor visit this Bool</code>.
     * 
     * @param visitor an <code>ASTVisitor</code> that should <code>visit this Bool</code>
     */
    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
    
    /**
     * Obtain <code>theValue</code> of <code>this Bool</code>.
     * 
     * @param answerTable an <code>AnswerTable</code> that is not used, may also
     *                      be <code>null</code>
     * @return a <code>BooleanValue</code> representing <code>theValue</code> of
     *          <code>this Bool</code>
     */
    @Override
    public BooleanValue eval(AnswerTable answerTable) {
        return new BooleanValue(value);
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
        return value.equals(other.value);
    }
    
    /**
     * @return an <code>int</code> containing a hash for <code>this Bool</code>
     */
    @Override
    public int hashCode() {
        return HASH_ORIGIN + value.hashCode();
    }
    
}
