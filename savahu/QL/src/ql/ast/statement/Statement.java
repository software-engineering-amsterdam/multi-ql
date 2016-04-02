/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ql.ast.statement;

import ql.ast.ASTNode;
import ql.ast.IVisitor;
import ql.ast.expression.Expr;
import ql.ast.form.Block;

/**
 *
 * @author sander
 */
public abstract class Statement extends ASTNode {

    private final Expr _IfCondition;
    private final Block _ThenStatement;

    public Statement(Expr ifCondition, Block thenStatement) {
        this._IfCondition = ifCondition;
        this._ThenStatement = thenStatement;
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }

    public Expr getCondition() {
        return this._IfCondition;
    }

    public Block getThenStatement() {
        return this._ThenStatement;
    }
}
