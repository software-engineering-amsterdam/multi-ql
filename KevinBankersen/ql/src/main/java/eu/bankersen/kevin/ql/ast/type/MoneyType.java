package eu.bankersen.kevin.ql.ast.type;

import java.math.BigDecimal;

public class MoneyType extends NumberType {
    
    private int round = BigDecimal.ROUND_HALF_DOWN;

    public boolean isSimilar(Type type) {
	return type instanceof MoneyType;
    }
    
    @Override
    public String toString() {
	return "Money";
    }
    
    @Override
    public BigDecimal parseValue(String value) {
	try {
	    return new BigDecimal(value).setScale(2, round);
	} catch (NumberFormatException e) {
	    return null;
	}
    }
    
    @Override
    public BigDecimal parseValue(Double value) {
	return new BigDecimal(value).setScale(2, round);
    }
    
    @Override
    public String formatTypeToString(String value) {
	return "€" + parseValue(value).toString();
    }
}
