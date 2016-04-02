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

public abstract class BinaryExpr extends Expr{
    
    private final Expr lhs;
    private final Expr rhs;
    
    public BinaryExpr(Expr theLhs, Expr theRhs) {
       
        this.lhs = theLhs;
        this.rhs = theRhs;
        
  }
    
  public Expr getLhs(){
      return lhs;
  }
  
  public Expr getRhs(){
      return rhs;
  }

    @Override
    public <T> T accept(TypecheckInterface<T> visitor) {
        return visitor.visit(this);
    }
    
    @Override
    public abstract Type getType();
}
