/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ql.ast.literal;

import ql.ast.IVisitor;
import ql.ast.type.BoolType;
import ql.ast.type.Type;

/**
 *
 * @author sander
 */
public class BoolLiteral extends Literal {

    private final Boolean value;

    public BoolLiteral(Boolean value) {
        this.value = value;
    }

    public Boolean getValue() {
        return this.value;
    }

    @Override
    public Type getType() {
        return new BoolType();
    }

    @Override
    public void visitChildren(IVisitor visitor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }

}
