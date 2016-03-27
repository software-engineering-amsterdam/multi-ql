/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST.form;

import AST.expressions.Expr;
import java.util.ArrayList;
import java.util.List;
//import typechecker.TypecheckInterface;
/**
 *
 * @author Dominique
 */
public abstract class Statement extends Expr{
    
    private final Expr ifstatement;
    private final Block thenstatement;
    //private List<Expr> ifstatementlist;
    //private List<Block> thenstatementlist;
    
    
    public Statement(Expr ifstatement, Block thenstatement){
        this.ifstatement = ifstatement;
        this.thenstatement = thenstatement;
    }
    
    public Block getThenStatement(){
        return this.thenstatement;
    }
    
    public Expr getIfStatement(){
        return this.ifstatement;
    }
    
}
