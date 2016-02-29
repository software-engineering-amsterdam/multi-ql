package nl.nicasso.ql.ast.expression.relational;

import nl.nicasso.ql.ast.expression.Polynomial;
import nl.nicasso.ql.ast.type.Type;

public abstract class Relational extends Polynomial {

	public Type checkAllowedTypes(Type left, Type right) {
		
		if (left.compatibleWith(right)) {
			if (left.getType().equals(right.getType())) {
				return left;
			}
		}
		
		return null;		
	}

}