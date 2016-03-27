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
public class Div extends Expr {

    private final Expr _Lhs, _Rhs;

    public Div(Expr lhs, Expr rhs) {
        this._Lhs = lhs;
        this._Rhs = rhs;
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void visitChildren(IVisitor visitor) {
        this._Lhs.accept(visitor);
        this._Rhs.accept(visitor);
    }

    @Override
    public Type getType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
