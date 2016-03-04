package nl.uva.sea.ql.ast.expr;

import java.util.Map;
import nl.uva.sea.ql.ast.question.Question;
import nl.uva.sea.ql.checker.ASTVisitor;

/**
 * Representation of <code>Expr</code>s which are sure to have a numeric value
 * and have two arguments in an AST.
 * 
 * @author Olav Trauschke
 * @version 4-mrt-2016
 */
public abstract class BinaryNumericOperatorExpr extends NumericExpr {
    
    /**
     * Start value used to calculate hashes for objects of this class.
     */
    public static final int HASH_ORIGIN = 5;
    
    /**
     * Factor partial hashes are multiplied by to generate a hash for objects of this class.
     */
    public static final int HASH_FACTOR = 97;
    
    private final Expr firstExpr;
    private final Expr secondExpr;
    private boolean isDecimal;
    private boolean isInt;
    private boolean isMoney;
    
    /**
     * Constructor for objects of class <code>BinaryNumericOperatorExpr</code>.
     * 
     * @param theFirstExpr the <code>Expr</code> on the left hand side of the operator
     * @param theSecondExpr the <code>Expr</code> on the right hand side of the operator
     */
    public BinaryNumericOperatorExpr(Expr theFirstExpr, Expr theSecondExpr) {
        assert theFirstExpr != null && theSecondExpr != null;
        firstExpr = theFirstExpr;
        secondExpr = theSecondExpr;
    }
    
    /**
     * Has the <code>firstExpr</code> and the <code>secondExpr</code> of
     * <code>this BinaryNumericOperatorExpr accept v</code> and then has
     * <code>v visit this BinaryNumericOperatorExpr</code>.
     * 
     * @param v an <code>ASTVisitor</code> that should
     *          <code>visit this BinaryNumericOperatorExpr</code> and its children
     */
    @Override
    public void accept(ASTVisitor v) {
        firstExpr.accept(v);
        secondExpr.accept(v);
        
        v.visit(this);
    }
    
    /**
     * @param questionTypes a <code>Map</code> from each
     *                      <code>Ident this BinaryNumericOperatorExpr</code>
     *                      might contain to a <code>Question</code> with that
     *                      <code>Ident</code>
     * @return whether <code>this BinaryNumericOperatorExpr</code> was set to
     *          represent a decimal value
     */
    @Override
    public boolean isDecimal(Map<Ident,Question> questionTypes) {
        return isDecimal;
    }
    
    /**
     * @param questionTypes a <code>Map</code> from each
     *                      <code>Ident this BinaryNumericOperatorExpr</code>
     *                      might contain to a <code>Question</code> with that
     *                      <code>Ident</code>
     * @return whether <code>this BinaryNumericOperatorExpr</code> was set to
     *          represent an integer value
     * 
     */
    @Override
    public boolean isInt(Map<Ident,Question> questionTypes) {
        return isInt;
    }
    
    /**
     * @param questionTypes a <code>Map</code> from each
     *                      <code>Ident this BinaryNumericOperatorExpr</code>
     *                      might contain to a <code>Question</code> with that
     *                      <code>Ident</code>
     * @return whether <code>this BinaryNumericOperatorExpr</code> was set to
     *          represent a money value
     */
    @Override
    public boolean isMoney(Map<Ident,Question> questionTypes) {
        return isMoney;
    }
    
    /**
     * Set whether <code>this BinaryNumericOperatorExpr</code> represents a
     * decimal value.
     * 
     * @param newValue whether or not <code>this BinaryNumericOperatorExpr</code>
     *                  represents a decimal value
     */
    public void setIsDecimal(boolean newValue) {
        isDecimal = newValue;
    }
    
    /**
     * Set whether <code>this BinaryNumericOperatorExpr</code> represents an int
     * value and set that <code>this BinaryNumericOperatorExpr</code> represents
     * a decimal value when the <code>newValue</code> is <code>true</code>
     * (because an integer value is decimal by definition).
     * N.B. <code>isDecimal</code> is NOT set when <code>newValue</code> is
     * <code>false</code> because a non-integer value could still be decimal.
     * 
     * @param newValue whether or not <code>this BinaryNumericOperatorExpr</code>
     *                  represents an integer value
     */
    public void setIsInt(boolean newValue) {
        isInt = newValue;
    }
    
    /**
     * Set whether <code>this BinaryNumericOperatorExpr</code> represents a money
     * value.
     * 
     * @param newValue whether or not <code>this BinaryNumericOperatorExpr</code>
     *                  reprsents a money value
     */
    public void setIsMoney(boolean newValue) {
        isMoney = newValue;
    }
    
    /**
     * Compares <code>this BinaryNumericOperatorExpr</code> to another
     * <code>Object</code>. A <code>BinaryNumericOperatorExpr</code> is
     * considered equal only to other objects of the same class as
     * <code>this BinaryNumericOperatorExpr</code>, for which <code>theFirstExpr</code>
     * and <code>theSecondExpr</code> are equal to its own values for these field.
     * 
     * @param o the <code>Object</code> to compare to <code>this BinaryNumericOperatorExpr</code>
     * @return <code>true</code> if and only if o is equal to
     *          <code>this BinaryNumericOperatorExpr</code> 
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        
        BinaryNumericOperatorExpr other = (BinaryNumericOperatorExpr) o;
        return firstExpr.equals(other.firstExpr) && secondExpr.equals(other.secondExpr);
    }
    
    /**
     * @return an <code>int</code> containing a hash for
     *          <code>this BinaryNumericOperatorExpr</code>
     */
    @Override
    public int hashCode() {
        int hash = HASH_ORIGIN;
        hash = HASH_FACTOR * hash + firstExpr.hashCode();
        hash = HASH_FACTOR * hash + secondExpr.hashCode();
        return hash;
    }
    
}
