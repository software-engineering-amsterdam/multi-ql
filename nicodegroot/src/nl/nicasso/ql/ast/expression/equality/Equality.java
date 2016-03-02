package nl.nicasso.ql.ast.expression.equality;

import nl.nicasso.ql.ast.CodeLocation;
import nl.nicasso.ql.ast.expression.Polynomial;
import nl.nicasso.ql.ast.type.Type;

public abstract class Equality extends Polynomial {

	public Equality(CodeLocation location) {
		super(location);
	}

	public Type checkAllowedTypes(Type left, Type right) {		
		if (left.getType().equals(right.getType())) {
				return left;
		}
		
		return null;		
	}

}
