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
public class IfElseStatement extends Statement {
    
    private Block _ElseStatement;

    public IfElseStatement(Expr ifCondition, Block thenStatement) {
        super(ifCondition, thenStatement);
    }
    
    public IfElseStatement(Expr ifCondition, Block thenStatement, Block elseStatement) {
        super(ifCondition, thenStatement);
        this._ElseStatement = elseStatement;
    }



}
