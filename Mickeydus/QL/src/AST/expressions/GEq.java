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
public class GEq extends BinaryExpr {

    public GEq(Expr left, Expr right) {
        super(left, right);
    }
    
//    public Boolean GreatherEquals(){
//        if(left==right){
//            return true;
//        }
//        else return left>right;
//}
}
