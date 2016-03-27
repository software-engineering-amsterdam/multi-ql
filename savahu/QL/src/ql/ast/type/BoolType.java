package ql.ast.type;

import ql.ast.IVisitor;

/**
 *
 * @author sander
 */
public class BoolType extends Type {

    public BoolType() {
        super();
    }

    @Override
    public Boolean isCompatibleToBoolean() {
        return true;
    }

    @Override
    public Boolean isCompatibleToString() {
        return false;
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
