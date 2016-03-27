package ql.ast.type;

import ql.ast.IVisitor;

public class IntType extends Type {

    public IntType() {
        super();
    }

    @Override
    public Boolean isCompatibleToBoolean() {
        return false;
    }

    @Override
    public Boolean isCompatibleToInteger() {
        return true;
    }

    @Override
    public Boolean isCompatibleToString() {
        return false;
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }

}
