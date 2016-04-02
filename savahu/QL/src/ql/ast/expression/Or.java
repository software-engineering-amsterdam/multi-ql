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
public class Or extends BinaryExpr {

    public Or(Expr lhs, Expr rhs) {
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
