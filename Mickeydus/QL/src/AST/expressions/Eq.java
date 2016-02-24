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
public class Eq extends BinaryExpr {

    private final Expr left;
    private final Expr right;
    
    public Eq(Expr left, Expr right) {
        super(left, right);
        this.left = left;
        this.right = right;
    }
    
    public Boolean Equals(){
        return left == right;
}
}
