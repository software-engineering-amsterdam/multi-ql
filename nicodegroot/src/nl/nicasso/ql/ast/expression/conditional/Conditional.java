package nl.nicasso.ql.ast.expression.conditional;

import nl.nicasso.ql.ast.CodeLocation;
import nl.nicasso.ql.ast.expression.Polynomial;
import nl.nicasso.ql.ast.type.BooleanType;
import nl.nicasso.ql.ast.type.Type;

public abstract class Conditional extends Polynomial {

	public Conditional(CodeLocation location) {
		super(location);
	}

	public Type checkAllowedTypes(Type left, Type right) {
		
		if (left.equals(right)) {
			if (left.equals(new BooleanType())) {
				return left;
			}
		}
		
		return null;
	}

}
