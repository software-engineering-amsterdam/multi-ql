package nl.nicasso.ql.ast.expressions.conditional;

import nl.nicasso.ql.ast.CodeLocation;
import nl.nicasso.ql.ast.expressions.Binary;
import nl.nicasso.ql.ast.types.BooleanType;
import nl.nicasso.ql.ast.types.Type;
import nl.nicasso.ql.ast.types.UnknownType;

public abstract class Conditional extends Binary {

	public Conditional(CodeLocation location) {
		super(location);
	}

	@Override
	public Type inferType(Type left, Type right) {
		
		if (left.equals(right)) {
			if (left.equals(new BooleanType())) {
				return left;
			}
		}
		
		return new UnknownType();
	}

}
