/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ql.ast.literal;

import ql.ast.IVisitor;
import ql.ast.type.StringType;
import ql.ast.type.Type;

/**
 *
 * @author sander
 */
public class StringLiteral extends Literal {

    private final String value;

    public StringLiteral(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public Type getType() {
        return new StringType();
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
