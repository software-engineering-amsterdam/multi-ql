/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST.literals;

import AST.expressions.Expr;
import AST.types.Type;
import typechecker.TypecheckInterface;

/**
 *
 * @author Dominique
 */
public abstract class Literal extends Expr {
    private Boolean isLiteral = true;
    
    @Override
    public void setLiteral() {
        this.isLiteral = true;
    }

    @Override
    public Boolean isLiteral() {
        return this.isLiteral;
    }   
    
    @Override
    public abstract Type getType();
}