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
public class OneExpr extends Expr{
    
    private final Expr onevar;
    
    public OneExpr(Expr onevar) {
        
        this.onevar = onevar;
  }
}
