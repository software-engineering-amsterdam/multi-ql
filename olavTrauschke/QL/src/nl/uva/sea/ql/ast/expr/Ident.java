package nl.uva.sea.ql.ast.expr;

import java.util.Map;
import nl.uva.sea.ql.ast.question.Question;
import nl.uva.sea.ql.checker.ASTVisitor;

/**
 * Representation of <code>Ident</code>s for questions in an AST.
 * 
 * @author Olav Trauschke
 * @version 4-mrt-2016
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
     * Has <code>v visit this Ident</code>.
     * 
     * @param v an <code>ASTVisitor</code> that should <code>visit this Ident</code>
     */
    @Override
    public void accept(ASTVisitor v) {
        v.visit(this);
    }
    
    /**
     * Returns whether <code>this Ident</code> represents a boolean value.
     * 
     * @param questionTypes a <code>Map</code> from each <code>Ident</code>
     *                      <code>this Ident</code> might be to a
     *                      <code>Question</code> with that <code>Ident</code>
     * @return <code>true</code> if and only if <code>questionTypes</code>
     *          maps <code>this Ident</code> to a <code>Question</code> for which
     *          {@link nl.uva.sea.ql.ast.question.Question#isBoolean() isBoolean()}
     *          returns <code>true</code> or does not map <code>this Ident</code>
     *          at all (to prevent unnecessary error messages)
     */
    @Override
    public boolean isBoolean(Map<Ident,Question> questionTypes) {
        if (!questionTypes.containsKey(this)) return true;
        
        Question q = questionTypes.get(this);
        return q.isBoolean();
    }
    
    /**
     * Returns whether <code>this Ident</code> represents a decimal value.
     * 
     * @param questionTypes a <code>Map</code> from each <code>Ident this Ident</code>
     *                      might be to a <code>Question</code> with that
     *                      <code>Ident</code>
     * @return <code>true</code> if and only if <code>questionTypes</code>
     *          maps <code>this Ident</code> to a <code>Question</code> for which
     *          {@link nl.uva.sea.ql.ast.question.Question#isDecimal() isDecimal()}
     *          returns <code>true</code> or does not map <code>this Ident</code>
     *          at all (to prevent unnecessary error messages)
     */
    @Override
    public boolean isDecimal(Map<Ident,Question> questionTypes) {
        if (!questionTypes.containsKey(this)) return true;
        
        Question q = questionTypes.get(this);
        return q.isDecimal();
    }
    
    /**
     * Returns whether <code>this Ident</code> represents an integer value.
     * 
     * @param questionTypes a <code>Map</code> from each <code>Ident this Ident</code>
     *                      might be to a <code>Question</code> with that
     *                      <code>Ident</code>
     * @return <code>true</code> if and only if <code>questionTypes</code>
     *          maps <code>this Ident</code> to a <code>Question</code> for which
     *          {@link nl.uva.sea.ql.ast.question.Question#isInt() isInt()} returns
     *          <code>true</code> or does not map <code>this Ident</code> at all
     *          (to prevent unnecessary error messages)
     */
    public boolean isInt(Map<Ident,Question> questionTypes) {
        if (!questionTypes.containsKey(this)) return true;
        
        Question q = questionTypes.get(this);
        return q.isInt();
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
    
    /**
     * @return <code>theContent</code>, representing <code>this Ident</code>
     */
    @Override
    public String toString() {
        return content;
    }
    
}
