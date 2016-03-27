/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST.expressions;

import AST.types.Type;
import typechecker.TypecheckInterface;

/**
 *
 * @author Dominique
 */
public class OneExpr extends Expr{
    
    private final Expr onevar;
    
    public OneExpr(Expr theVariable) {
        
        this.onevar = theVariable;
  }

    @Override
    public <T> T accept(TypecheckInterface<T> visitor) {
     return visitor.visit(this);
    }

    @Override
    public Type getType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
