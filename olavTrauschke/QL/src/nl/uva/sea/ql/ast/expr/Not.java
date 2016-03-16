package nl.uva.sea.ql.ast.expr;

import nl.uva.sea.ql.answerTable.AnswerTable;
import nl.uva.sea.ql.answerTable.BooleanValue;
import nl.uva.sea.ql.checker.ASTVisitor;

/**
 * Representation of a (boolean) negation in an AST.
 * 
 * @author Olav Trauschke
 * @version 16-mar-2016
 */
public class Not extends BooleanExpr {
    
    /**
     * Start value used to calculate hashes for objects of this class.
     */
    public static final int HASH_ORIGIN = 65;
    
    private final Expr content;
    
    /**
     * Constructor for objects of class <code>Not</code>.
     * 
     * @param theContent an <code>Integer</code> representing the expression
     *                      that is negated
     */
    public Not(Expr theContent) {
        assert theContent != null;
        content = theContent;
    }
    
    /**
     * @return <code>theContent</code> of <code>this Not</code>
     */
    public Expr getContent() {
        return content;
    }
    
    /**
     * Has <code>theContent</code> of <code>this Not accept visitor</code> and then
     * has <code>visitor visit this Not</code>.
     * 
     * @param visitor an <code>ASTVisitor</code> that should <code>visit this Not</code>
     *          and its children
     */
    @Override
    public void accept(ASTVisitor visitor) {
        content.accept(visitor);
        
        visitor.visit(this);
    }
    
    /**
     * Evaluate <code>this Not</code>.
     * 
     * @param answerTable an <code>AnswerTable</code> mapping all <code>Ident</code>s
     *                      that might appear in <code>theContent</code> of
     *                      <code>this Not</code> to the <code>Value</code>s of
     *                      the <code>Question</code>s they represent
     * @return a <code>BooleanValue</code> representing the result of negating
     *          <code>theContent</code> of <code>this Not</code> 
     */
    @Override
    public BooleanValue eval(AnswerTable answerTable) {
        BooleanValue value = (BooleanValue) content.eval(answerTable);
        return value.negate();
    }
    
    /**
     * Compares <code>this Not</code> to another <code>Object</code>. A
     * <code>Not</code> is considered equal only to other objects of this class,
     * for which <code>theContent</code> is equal to its own value for this field.
     * 
     * @param o the <code>Object</code> to compare to <code>this Int</code>
     * @return <code>true</code> if and only if o is equal to <code>this Int</code> 
     */
    @Override
    public boolean equals(Object o) {
        return o != null
                && getClass() == o.getClass()
                && content.equals(((Not) o).content);
    }
    
    /**
     * @return an <code>int</code> containing a hash for <code>this Not</code>
     */
    @Override
    public int hashCode() {
        return HASH_ORIGIN + content.hashCode();
    }
    
}
