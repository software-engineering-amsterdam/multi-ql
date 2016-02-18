/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST.expressions;

import ql.Expr;

/**
 *
 * @author Dominique
 */
public class BinaryExpr extends Expr{
    
    private final Expr lhs;
    private final Expr rhs;
    
    public BinaryExpr(Expr lhs, Expr rhs) {
        
        this.lhs = lhs;
        this.rhs = rhs;
        
  }
    
  public Expr getLhs(){
      return lhs;
  }
  
  public Expr getRhs(){
      return rhs;
  }
}
