/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ql.ast.statement;

import ql.ast.expression.Expr;
import ql.ast.form.Block;

/**
 *
 * @author sander
 */
public class IfStatement extends Statement {

    public IfStatement(Expr ifCondition, Block thenStatement) {
        super(ifCondition, thenStatement);
    }




}
