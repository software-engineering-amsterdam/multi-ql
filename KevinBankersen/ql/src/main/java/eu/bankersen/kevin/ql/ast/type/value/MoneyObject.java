package eu.bankersen.kevin.ql.ast.type.value;

public class MoneyObject extends NumberObject {

    @Override
    public boolean isSimilar(QLObject type) {
	return type instanceof MoneyObject;
    }
    
    @Override
    public String toString() {
	return String.valueOf(super.value());
    }

}
