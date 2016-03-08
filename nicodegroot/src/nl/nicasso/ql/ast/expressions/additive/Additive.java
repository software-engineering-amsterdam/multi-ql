package nl.nicasso.ql.ast.expressions.additive;

import nl.nicasso.ql.ast.CodeLocation;
import nl.nicasso.ql.ast.expressions.Binary;
import nl.nicasso.ql.ast.types.IntegerType;
import nl.nicasso.ql.ast.types.MoneyType;
import nl.nicasso.ql.ast.types.Type;
import nl.nicasso.ql.ast.types.UnknownType;

public abstract class Additive extends Binary {

	public Additive(CodeLocation location) {
		super(location);
	}

	@Override
	public Type inferType(Type left, Type right) {
		
		if (left.equals(right)) {
			if (left.equals(new IntegerType()) || left.equals(new MoneyType())) {
				return left;
			}
		}
		
		return new UnknownType();		
	}

}