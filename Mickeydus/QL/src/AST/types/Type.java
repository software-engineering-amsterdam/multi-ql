/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST.types;

import ql.ASTNode;

/**
 *
 * @author Dominique
 */
public abstract class Type extends ASTNode {

    public Type() {
    }

    public abstract Boolean isString();

    public abstract Boolean isBoolean();

    public abstract Boolean isMoney();

    public abstract Boolean isInt();

}
