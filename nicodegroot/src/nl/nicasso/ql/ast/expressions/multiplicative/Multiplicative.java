package nl.nicasso.ql.ast.expressions.multiplicative;

import nl.nicasso.ql.ast.CodeLocation;
import nl.nicasso.ql.ast.expressions.Binary;
import nl.nicasso.ql.ast.types.IntegerType;
import nl.nicasso.ql.ast.types.Type;
import nl.nicasso.ql.ast.types.UnknownType;

public abstract class Multiplicative extends Binary {

	public Multiplicative(CodeLocation location) {
		super(location);
	}

	@Override
	public Type inferType(Type left, Type right) {
		
		if (left.equals(right)) {
			if (left.equals(new IntegerType())) {
				return left;
			}
		}
	
		return new UnknownType();		
	}

}