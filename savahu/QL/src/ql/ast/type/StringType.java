/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ql.ast.type;

import ql.ast.IVisitor;

/**
 *
 * @author sander
 */
public class StringType extends Type {

    public StringType() {
        super();
    }

    @Override
    public Boolean isCompatibleToBoolean() {
        return false;
    }

    @Override
    public Boolean isCompatibleToString() {
        return true;
    }

    @Override
    public Boolean isCompatibleToInteger() {
        return false;
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }
}
