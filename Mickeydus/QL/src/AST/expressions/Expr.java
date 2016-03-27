/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST.expressions;

import AST.types.Type;
import ql.ASTNode;

/**
 *
 * @author Dominique
 */
public abstract class Expr extends ASTNode {

    private Boolean isLiteral = false;

    public void setLiteral() {
        this.isLiteral = true;
    }
    
    public Boolean isLiteral() {
        return this.isLiteral;
    }

    public abstract Type getType();

}
