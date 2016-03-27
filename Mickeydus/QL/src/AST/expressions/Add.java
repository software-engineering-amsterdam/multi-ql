/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST.expressions;

import AST.types.Type;

/**
 *
 * @author Dominique
 */
public class Add extends BinaryExpr {
	public static final String str = "+";
    
    public Add(Expr left, Expr right) {
        super(left, right);
    }

    @Override
    public Type getType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
