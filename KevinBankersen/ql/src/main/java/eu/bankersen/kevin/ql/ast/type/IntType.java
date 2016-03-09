package eu.bankersen.kevin.ql.ast.type;

import java.math.BigDecimal;

public class IntType extends NumberType {

    @Override
    public boolean isSimilar(Type type) {
	return type instanceof IntType;
    }

    @Override
    public String toString() {
	return "Integer";
    }
    
    @Override
    public BigDecimal parseValue(String value) {
	try {
	    return new BigDecimal(value).setScale(0, BigDecimal.ROUND_HALF_DOWN);
	} catch (NumberFormatException e) {
	    return null;
	}
    }
    
    @Override
    public BigDecimal parseValue(Double value) {
	return new BigDecimal(value).setScale(0, BigDecimal.ROUND_HALF_DOWN);
    }

    @Override
    public String formatTypeToString(String value) {
	return parseValue(value).toString();
    }
}
