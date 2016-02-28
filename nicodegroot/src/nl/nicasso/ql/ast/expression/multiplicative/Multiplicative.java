package nl.nicasso.ql.ast.expression.multiplicative;

import nl.nicasso.ql.ast.expression.Polynomial;
import nl.nicasso.ql.ast.type.IntegerType;
import nl.nicasso.ql.ast.type.MoneyType;
import nl.nicasso.ql.ast.type.Type;

public abstract class Multiplicative extends Polynomial {

	public Type checkAllowedTypes(Type left, Type right) {
		
		if (left.compatibleWith(right)) {
			if (left.getType().equals(right.getType())) {
				if (left.getType().equals(new IntegerType().getType()) || left.getType().equals(new MoneyType().getType())) {
					return new MoneyType();
				}
			}
		}
		
		return null;		
	}

}