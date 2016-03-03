package eu.bankersen.kevin.ql.util.testing.old;

import eu.bankersen.kevin.ql.ast.Type;

public class IntegerType extends ValueObject {

    public IntegerType() {
	super(Type.INTEGER, Type.EMPTY);
    }
    
    public IntegerType(Integer value) {
	super(Type.INTEGER, value);
    }
}
