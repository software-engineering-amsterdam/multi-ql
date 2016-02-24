package org.uva.sea.ql.ast.expr;

/**
 * Representation of <code>Expr</code>s which are sure to have a numeric value
 * and have one argument in an AST.
 * 
 * @author Olav Trauschke
 * @version 24-feb-2016
 */
public abstract class UnaryNumericOperatorExpr extends NumericExpr {
    
    /**
     * Start value used to calculate hashes for objects of this class.
     */
    public static final int HASH_ORIGIN = 155;
    
    private final Expr content;
    
    /**
     * Constructor for objects of class <code>UnaryNumericOperatorExpr</code>.
     * 
     * @param theContent the <code>Expr</code> the operator is applied on
     */
    public UnaryNumericOperatorExpr(Expr theContent) {
        assert theContent != null;
        content = theContent;
    }
    
    /**
     * Compares <code>this UnaryNumericOperatorExpr</code> to another
     * <code>Object</code>. A <code>UnaryNumericOperatorExpr</code> is
     * considered equal only to other objects of the same class as
     * <code>this BinaryNumericOperatorExpr</code>, for which <code>theContent</code>
     * is equal to its own value for this field.
     * 
     * @param o the <code>Object</code> to compare to <code>this BinaryNumericOperatorExpr</code>
     * @return <code>true</code> if and only if o is equal to
     *          <code>this BinaryNumericOperatorExpr</code> 
     */
    @Override
    public boolean equals(Object o) {
        return o != null
                && getClass() == o.getClass()
                && content.equals(((UnaryNumericOperatorExpr) o).content);
    }
    
    /**
     * @return an <code>int</code> containing a hash for
     *          <code>this UnaryNumericOperatorExpr</code>
     */
    @Override
    public int hashCode() {
        return HASH_ORIGIN + content.hashCode();
    }
    
}
