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

    @Override
    public Type getType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
