package org.uva.sea.ql.ast.expr;

import org.uva.sea.ql.checker.ASTVisitor;

/**
 * Representation of <code>Expr</code>s which are sure to have a numeric value
 * and have two arguments in an AST.
 * 
 * @author Olav Trauschke
 * @version 25-feb-2016
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
