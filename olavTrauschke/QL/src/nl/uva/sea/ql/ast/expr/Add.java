package nl.uva.sea.ql.ast.expr;

import java.util.Map;
import nl.uva.sea.ql.ast.question.Question;
import nl.uva.sea.ql.checker.ASTVisitor;

/**
 * Representation of the + operator in an AST, that can either mean addition or
 * (string) concatenation.
 * 
 * @author Olav Trauschke
 * @version 4-mar-2016
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
        assert theFirstExpr != null & theSecondExpr != null;
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
     * <code>this Add accept v</code> and then has <code>v visit this Add</code>.
     * 
     * @param v an <code>ASTVisitor</code> that should
     *          <code>visit this Add</code> and its children
     */
    @Override
    public void accept(ASTVisitor v) {
        firstExpr.accept(v);
        secondExpr.accept(v);
        
        v.visit(this);
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
     * Set whether <code>this Add</code> represents a decimal value.
     * 
     * @param newValue whether or not <code>this Add</code> represents a decimal
     *                  value
     */
    public void setIsDecimal(boolean newValue) {
        isDecimal = newValue;
    }
    
    /**
     * Set wheter <code>this Add</code> represents an integer value.
     * 
     * @param newValue whether or not <code>this Add</code> represents an int value
     */
    public void setIsInt(boolean newValue) {
        isInt = newValue;
    }
    
    /**
     * Set whether <code>this Add</code> repersents a money value.
     * 
     * @param newValue whether or not <code>this Add</code> represents a money value
     */
    public void setIsMoney(boolean newValue) {
        isMoney = newValue;
    }
    
    /**
     * Set whether <code>this Add</code> represents a string value.
     * 
     * @param newValue whether or not <code>this Add</code> represents a string
     *                  value
     */
    public void setIsString(boolean newValue) {
        isString = newValue;
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
