/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ql.ast.expression;

/**
 *
 * @author sander
 */
public class UnaryExpr extends Expr {
    private final Expr value;
    
    public UnaryExpr(Expr value)
    {
        this.value = value;
    }
    
    @Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!getClass().equals(obj.getClass())) {
			return false;
		}
		UnaryExpr un = (UnaryExpr)obj;
		return getValue().equals(un.getValue());
	}
        
        public Expr getValue()
        {
            return this.value;
        }
}
