package nl.uva.sea.ql.ast.expr;

import java.util.Map;
import nl.uva.sea.ql.ast.question.Question;
import nl.uva.sea.ql.checker.ASTVisitor;

/**
 * Representation of a a minus in front of a number in an AST.
 * 
 * @author Olav Trauschke
 * @version 3-mrt-2016
 */
public class Neg extends NumericExpr {
    
    /**
     * Start value used to calculate hashes for objects of this class.
     */
    public static final int HASH_ORIGIN = 155;
    
    private final Expr content;
    
    /**
     * Constructor for objects of class <code>Neg</code>.
     * 
     * @param theContent the <code>Expr</code> after the operator
     */
    public Neg(Expr theContent) {
        assert theContent != null;
        content = theContent;
    }
    
    /**
     * Has <code>theContent</code> of <code>this ComparisonExpr accept v</code>
     * and then has <code>v visit this Neg</code>.
     * 
     * @param v an <code>ASTVisitor</code> that should
     *          <code>visit this Neg</code> and its children
     */
    @Override
    public void accept(ASTVisitor v) {
        content.accept(v);
        
        v.visit(this);
    }
    
    /**
     * @param questionTypes a <code>Map</code> from each <code>Ident</code>
     *                      <code>this Neg</code> might contain to a
     *                      <code>Question</code> with that <code>Ident</code>
     * @return whether <code>this Neg</code> represents a decimal value
     */
    @Override
    public boolean isDecimal(Map<Ident,Question> questionTypes) {
        return content.isDecimal(questionTypes);
    }
    
    /**
     * Compares <code>this Neg</code> to another <code>Object</code>. A
     * <code>Neg</code> is considered equal only to other objects of the same
     * class as <code>this Neg</code>, for which <code>theContent</code>
     * is equal to its own value for this field.
     * 
     * @param o the <code>Object</code> to compare to <code>this Neg</code>
     * @return <code>true</code> if and only if o is equal to <code>this Neg</code> 
     */
    @Override
    public boolean equals(Object o) {
        return o != null
                && getClass() == o.getClass()
                && content.equals(((Neg) o).content);
    }
    
    /**
     * @return an <code>int</code> containing a hash for <code>this Neg</code>
     */
    @Override
    public int hashCode() {
        return HASH_ORIGIN + content.hashCode();
    }
}
