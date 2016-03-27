/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ql.ast.expression;

import ql.ast.IVisitor;
import ql.ast.type.Type;

/**
 *
 * @author sander
 */
public abstract class UnaryExpr extends Expr {

    private final Expr _Value;

    public UnaryExpr(Expr value) {
        this._Value = value;
    }

    public Expr getValue() {
        return this._Value;
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void visitChildren(IVisitor visitor) {
        this._Value.accept(visitor);
    }

    @Override
    public Type getType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
