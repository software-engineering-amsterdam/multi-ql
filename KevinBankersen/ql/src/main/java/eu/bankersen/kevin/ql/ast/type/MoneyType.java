package eu.bankersen.kevin.ql.ast.type;

import eu.bankersen.kevin.ql.ast.Type;

public class MoneyType extends TypeObject {

    private final Type type = Type.MONEY;
       
    @Override
    public Type getType() {
	return type;
    }

    @Override
    public boolean compatible(TypeObject typeObject) {
	switch(typeObject.getType()) {
	case MONEY: return true;
	case INTEGER: return true;
	default : return false;
	}
    }

}
