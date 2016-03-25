package nl.uva.sea.ql.ast.expr;

import java.util.Map;
import nl.uva.sea.ql.answerTable.*;
import nl.uva.sea.ql.ast.question.Question;
import nl.uva.sea.ql.generalPurposeVisitors.ASTVisitor;

/**
 * Representation of a a minus in front of a number in an AST.
 * 
 * @author Olav Trauschke
 * @version 17-mar-2016
 */
public class Neg extends NumericExpr {
    
    /**
     * Start value used to calculate hashes for objects of this class.
     */
    public static final int HASH_ORIGIN = 155;
    
    /**
     * <code>IntValue</code> representing the factor <code>NumericValue</code>
     * should be multiplied by to negate them.
     */
    public static final IntValue MULTIPLICATION_FACTOR = new IntValue(-1);
    
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
     * @return the <code>Expr</code> after the operator
     */
    public Expr getContent() {
        return content;
    }
    
    /**
     * Has <code>theContent</code> of <code>this ComparisonExpr accept visitor</code>
     * and then has <code>visitor visit this Neg</code>.
     * 
     * @param visitor an <code>ASTVisitor</code> that should
     *                  <code>visit this Neg</code> and its children
     */
    @Override
    public void accept(ASTVisitor visitor) {
        content.accept(visitor);
        
        visitor.visit(this);
    }
    
    /**
     * @param questionTypes a <code>Map</code> from each
     *                      <code>Ident this Neg</code> might contain to a
     *                      <code>Question</code> with that <code>Ident</code>
     * @return whether <code>this Neg</code> represents a decimal value
     */
    @Override
    public boolean isDecimal(Map<Ident,Question> questionTypes) {
        return content.isDecimal(questionTypes);
    }
    
    /**
     * @param questionTypes a <code>Map</code> from each
     *                      <code>Ident this Neg</code> might contain to a
     *                      <code>Question</code> with that <code>Ident</code>
     * @return whether <code>this Neg</code> represents an integer value
     */
    @Override
    public boolean isInt(Map<Ident,Question> questionTypes) {
        return content.isInt(questionTypes);
    }
    
    /**
     * @param questionTypes a <code>Map</code> from each
     *                      <code>Ident this Neg</code> might contain to a
     *                      <code>Question</code> with that <code>Ident</code>
     * @return whether <code>thisNeg</code> represents a money value
     */
    @Override
    public boolean isMoney(Map<Ident,Question> questionTypes) {
        return content.isMoney(questionTypes);
    }
    
    /**
     * Evaluate <code>this Neg</code>.
     * 
     * @param answerTable an <code>AnswerTable</code> mapping all <code>Ident</code>s
     *                      that might appear in <code>theContent</code> of
     *                      <code>this Neg</code> to the <code>Value</code> of
     *                      the <code>Question</code> they represent
     * @return a <code>NumericValue</code> representing the negative of
     *          <code>theContent</code>
     */
    @Override
    public NumericValue eval(AnswerTable answerTable) {
        NumericValue value = (NumericValue) content.eval(answerTable);
        return negate(value);
    }
    
    /**
     * Obtain the negative of a <code>NumericValue</code>. The negative variant
     * of a <code>NumericValue</code> is calculated by multiplying it by
     * {@link #MULTIPLICATION_FACTOR MULTIPLICATION_FACTOR}.
     * 
     * @param value a <code>NumericValue</code> to get the negative variant of
     * @return the negative variant of <code>value</code>
     */
    public static NumericValue negate(NumericValue value) {
        return value.multiply(MULTIPLICATION_FACTOR);
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
