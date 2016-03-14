package eu.bankersen.kevin.ql.ast.values;

import eu.bankersen.kevin.ql.ast.types.QLType;
import eu.bankersen.kevin.ql.ast.types.UndifinedType;

public class UndifinedValue extends AbstractValue {

    @Override
    public Object value() {
	return null;
    }

    @Override
    public String toString() {
	return "Empty";
    }

    @Override
    public QLType getType() {
	return new UndifinedType();
    }

    @Override
    public Boolean equals(QLValue type) {
	return type.equals(this);
    }

    @Override
    public Boolean equals(UndifinedValue type) {
	return true;
    }
}
