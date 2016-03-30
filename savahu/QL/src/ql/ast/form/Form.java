/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ql.ast.form;

import ql.ast.ASTNode;
import ql.ast.expression.Ident;

/**
 *
 * @author sander
 */
public class Form implements ASTNode {

    private final Ident _Identifier;
    private final Block _Block;

    public Form(Ident identifier, Block block) {
        this._Identifier = identifier;
        this._Block = block;
    }

    public Ident getIdentifier() {
        return this._Identifier;
    }
}
