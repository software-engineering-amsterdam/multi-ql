package ql.ast.expression;

/**
 *
 * @author sander
 */
public class BinaryExpr  extends Expr {
    private final Expr lhs, rhs;
    
    public BinaryExpr(Expr lhs, Expr rhs)
    {
        this.lhs = lhs;
        this.rhs = rhs;
    }
    
    @Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!getClass().equals(obj.getClass())) {
			return false;
		}
		BinaryExpr bin = (BinaryExpr)obj;
		return getLhs().equals(bin.getLhs()) && getRhs().equals(bin.getRhs());
	}
        
        public Expr getLhs()
        {
            return this.lhs;
        }
        
        public Expr getRhs()
        {
            return this.rhs;
        }
}
