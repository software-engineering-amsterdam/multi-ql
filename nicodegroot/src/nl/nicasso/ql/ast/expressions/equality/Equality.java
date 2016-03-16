package nl.nicasso.ql.ast.expressions.equality;

import nl.nicasso.ql.ast.CodeLocation;
import nl.nicasso.ql.ast.expressions.Binary;
import nl.nicasso.ql.ast.types.BooleanType;
import nl.nicasso.ql.ast.types.Type;
import nl.nicasso.ql.ast.types.UnknownType;

public abstract class Equality extends Binary {

	public Equality(CodeLocation location) {
		super(location);
	}

	@Override
	public Type inferType(Type left, Type right) {		
		if (left.equals(right)) {
			return new BooleanType();
		}
		
		return new UnknownType();		
	}

}
