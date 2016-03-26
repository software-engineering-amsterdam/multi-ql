package nl.uva.sea.ql.ast.expr;

import java.util.Map;
import nl.uva.sea.ql.answerTable.*;
import nl.uva.sea.ql.ast.question.Question;
import nl.uva.sea.ql.generalPurposeVisitors.ASTVisitor;

/**
 * Representation of the + operator in an AST, that can either mean addition or
 * (string) concatenation.
 * 
 * @author Olav Trauschke
 * @version 26-mar-2016
 */
public class Add extends Expr {
    
    /**
     * Start value used to calculate hashes for objects of this class.
     */
    public static final int HASH_ORIGIN = 7;
    
    /**
     * Factor partial hashes are multiplied by to generate a hash for objects of this class.
     */
    public static final int HASH_FACTOR = 67;
    
    private final Expr firstExpr;
    private final Expr secondExpr;
    private boolean isDecimal;
    private boolean isInt;
    private boolean isMoney;
    private boolean isString;
    
    /**
     * Constructor for objects of class <code>Add</code>.
     * 
     * @param theFirstExpr the <code>Expr</code> on the left hand side of the operator
     * @param theSecondExpr the <code>Expr</code> on the right hand side of the operator
     */
    public Add(Expr theFirstExpr, Expr theSecondExpr) {
        assert theFirstExpr != null && theSecondExpr != null;
        firstExpr = theFirstExpr;
        secondExpr = theSecondExpr;
    }
    
    /**
     * @return the <code>Expr</code> on the left hand side of the operator
     */
    public Expr getFirstExpr() {
        return firstExpr;
    }
    
    /**
     * @return the <code>Expr</code> on the right hand side of the operator
     */
    public Expr getSecondExpr() {
        return secondExpr;
    }
    
    /**
     * Has the <code>firstExpr</code> and the <code>secondExpr</code> of
     * <code>this Add accept visitor</code> and then has <code>visitor visit this Add</code>.
     * 
     * @param visitor an <code>ASTVisitor</code> that should
     *          <code>visit this Add</code> and its children
     */
    @Override
    public void accept(ASTVisitor visitor) {
        firstExpr.accept(visitor);
        secondExpr.accept(visitor);
        
        visitor.visit(this);
    }
    
    /**
     * @param questionTypes a <code>Map</code> from each <code>Ident this Add</code>
     *                      might contain to a <code>Question</code> with that
     *                      <code>Ident</code>
     * @return whether <code>this Add</code> was set to represent a decimal value
     */
    @Override
    public boolean isDecimal(Map<Ident,Question> questionTypes) {
        return isDecimal;
    }
    
    /**
     * @param questionTypes a <code>Map</code> from each <code>Ident this Add</code>
     *                      might contain to a <code>Question</code> with that
     *                      <code>Ident</code>
     * @return whether <code>this Add</code> was set to represent an integer value
     */
    @Override
    public boolean isInt(Map<Ident,Question> questionTypes) {
        return isInt;
    }
    
    /**
     * @param questionTypes a <code>Map</code> from each <code>Ident this Add</code>
     *                      might contain to a <code>Question</code> with that
     *                      <code>Ident</code>
     * @return whether <code>this Add</code> was set to represent a money value
     */
    @Override
    public boolean isMoney(Map<Ident,Question> questionTypes) {
        return isMoney;
    }
    
    /**
     * @param questionTypes a <code>Map</code> from each <code>Ident this Add</code>
     *                      might contain to a <code>Question</code> with that
     *                      <code>Ident</code>
     * @return whether <code>this Add</code> was set to reprsent a string value
     */
    @Override
    public boolean isString(Map<Ident,Question> questionTypes) {
        return isString;
    }
    
    /**
     * Set <code>this Add</code> to represent a decimal value.
     */
    public void setIsDecimal() {
        isDecimal = true;
    }
    
    /**
     * Set <code>this Add</code> to represent an integer value.
     */
    public void setIsInt() {
        isInt = true;
    }
    
    /**
     * Set <code>this Add</code> to repersent a money value.
     */
    public void setIsMoney() {
        isMoney = true;
    }
    
    /**
     * Set <code>this Add</code> to represent a string value.
     */
    public void setIsString() {
        isString = true;
    }
    
    /**
     * Evaluate <code>this Add</code>. Assumes either one or more of
     * <code>isDecimal</code>, <code>isInt</code> and <code>isMoney</code>, or
     * <code>isString</code> was set, but not both.
     * 
     * @param answerTable an <code>AnswerTable</code> mapping all <code>Ident</code>s
     *                      that might appear in <code>Expr</code>s in
     *                      <code>this Add</code> to the <code>Value</code> of
     *                      the <code>Question</code> they represent
     * @return a <code>Value</code> representing the result of adding
     *          <code>this Add</code>'s <code>secondExpr</code> to its
     *          <code>firstExpr</code> if <code>this Add isDecimal</code>,
     *          <code>isInt</code> or <code>isMoney</code> or the result of
     *          concatenating its <code>secondExpr</code> to its
     *          <code>firstExpr</code> otherwise (if it <code>isString</code>)
     */
    @Override
    public Value eval(AnswerTable answerTable) {
        boolean isNumeric = isDecimal || isInt || isMoney;
        assert (isNumeric && !isString) || (!isNumeric && isString);
        if (isNumeric) {
            return evalNumeric(answerTable);
        }
        else {
            return evalString(answerTable);
        }
    }
    
    /**
     * Evaluate <code>this Add</code> interpreting it as addition of
     * <code>NumericValue</code>s. Assumes <code>theFirstExpr</code> and
     * <code>theSecondExpr</code> evaluate to <code>NumericValue</code>s.
     * 
     * @param answerTable an <code>AnswerTable</code> mapping all <code>Ident</code>s
     *                      that might appear in <code>Expr</code>s in
     *                      <code>this Add</code> to the <code>Value</code> of
     *                      the <code>Question</code> they represent
     * @return a <code>NumericValue</code> representing the result of adding
     *          <code>this Add</code>'s <code>secondExpr</code> to its
     *          <code>firstExpr</code>
     */
    private NumericValue evalNumeric(AnswerTable answerTable) {
        NumericValue firstValue = NumericValue.cast(firstExpr.eval(answerTable));
        NumericValue secondValue = NumericValue.cast(secondExpr.eval(answerTable));
        return firstValue.add(secondValue);
    }
    
    /**
     * Evaluate <code>this Add</code> interpreting it as concatenation of
     * <code>StringValue</code>s. Assumes <code>theFirstExpr</code> and
     * <code>theSecondExpr</code> evaluate to <code>StringValue</code>s.
     * 
     * @param answerTable an <code>AnswerTable</code> mapping all <code>Ident</code>s
     *                      that might appear in <code>Expr</code>s in
     *                      <code>this Add</code> to the <code>Value</code> of
     *                      the <code>Question</code> they represent
     * @return a <code>StringValue</code> representing the result of concatenating
     *          <code>this Add</code>'s <code>secondExpr</code> to its
     *          <code>firstExpr</code>
     */
    private StringValue evalString(AnswerTable answerTable) {
        StringValue firstValue = StringValue.cast(firstExpr.eval(answerTable));
        StringValue secondValue = StringValue.cast(secondExpr.eval(answerTable));
        return firstValue.concat(secondValue);
    }
    
    /**
     * Compares <code>this Add</code> to another <code>Object</code>. An
     * <code>Add</code> is considered equal only to other objects of this class,
     * for which <code>theFirstExpr</code> and <code>theSecondExpr</code> equal
     * its own values for these fields.
     * 
     * @param o the <code>Object</code> to compare to <code>this Add</code>
     * @return <code>true</code> if and only if o is equal to <code>this Add</code> 
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        
        Add other = (Add) o;
        return firstExpr.equals(other.firstExpr) && secondExpr.equals(other.secondExpr);
    }
    
    /**
     * @return an <code>int</code> containing a hash for <code>this Add</code>
     */
    @Override
    public int hashCode() {
        int hash = HASH_ORIGIN;
        hash = HASH_FACTOR * hash + firstExpr.hashCode();
        hash = HASH_FACTOR * hash + secondExpr.hashCode();
        return hash;
    }
}
