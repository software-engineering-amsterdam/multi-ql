package eu.bankersen.kevin.ql.ast.type.value;

import java.math.BigDecimal;

public abstract class QLObject implements QLOperations {
    
    public QLObject() { }
    
    public abstract Object value();
    
    public abstract boolean isSimilar(QLObject type);

    public boolean isCompatible(QLObject type) {
	return isSimilar(type);
    }

    public abstract void valueOf(String value);

    public abstract void valueOf(BigDecimal value);
    
    public abstract void valueOf(Integer value);

    public abstract void valueOf(Boolean value);
}
