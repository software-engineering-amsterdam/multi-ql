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
public class Or extends BinaryExpr {
    
    public Or(Expr left, Expr right) {
        super(left, right);
    }
    
}
