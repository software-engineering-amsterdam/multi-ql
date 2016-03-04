package eu.bankersen.kevin.ql.ast.type;

public abstract class NumberType extends Type {

    @Override
    public boolean isCompatible(Type type) {
	return type instanceof IntType || type instanceof MoneyType;
    }
    
    public boolean isNumber() {
	return true;
    }
}
