package org.uva.sea.ql.ast.expr;

import org.uva.sea.ql.checker.ASTVisitor;

/**
 * Representation of the + operator in an AST.
 * 
 * @author Olav Trauschke, 10329463
 * @version 25-feb-2016
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
