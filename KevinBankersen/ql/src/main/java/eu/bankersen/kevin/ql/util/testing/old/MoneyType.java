package eu.bankersen.kevin.ql.util.testing.old;

import eu.bankersen.kevin.ql.ast.Type;

public class MoneyType extends ValueObject {

    public MoneyType() {
	super(Type.MONEY, Type.EMPTY);
    }
    
    public MoneyType(Integer value) {
	super(Type.MONEY, value);
    }
}
