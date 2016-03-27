/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ql.ast.expression;

import ql.ast.IVisitor;
import ql.ast.type.IntType;
import ql.ast.type.Type;

/**
 *
 * @author sander
 */
public class Sub extends BinaryExpr {

    public Sub(Expr lhs, Expr rhs) {
        super(lhs, rhs);
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void visitChildren(IVisitor visitor) {
        this.getLhs().accept(visitor);
        this.getRhs().accept(visitor);
    }

    @Override
    public Type getType() {
        return new IntType();
    }
}
