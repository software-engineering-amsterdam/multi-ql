/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ql.ast.form;

import ql.ast.expression.Ident;

/**
 *
 * @author sander
 */
public class Form {

    private final Ident Identifier;

    public Form(Ident identifier) {
        this.Identifier = identifier;
    }

    public Ident getIdentifier() {
        return this.Identifier;
    }
}
