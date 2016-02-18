/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ql.ast.type;

import ql.ast.expression.Expr;

/**
 *
 * @author sander
 */
public class Str extends Expr {
    private final String value;
    
    public Str(String stringValue)
    {
        this.value = stringValue;
    }
}
