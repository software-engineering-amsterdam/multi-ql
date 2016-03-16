package nl.nicasso.ql.ast.expressions.relational;

import nl.nicasso.ql.ast.CodeLocation;
import nl.nicasso.ql.ast.expressions.Binary;
import nl.nicasso.ql.ast.types.BooleanType;
import nl.nicasso.ql.ast.types.IntegerType;
import nl.nicasso.ql.ast.types.MoneyType;
import nl.nicasso.ql.ast.types.Type;
import nl.nicasso.ql.ast.types.UnknownType;

public abstract class Relational extends Binary {

	public Relational(CodeLocation location) {
		super(location);
	}

	@Override
	public Type inferType(Type left, Type right) {
		if (left.equals(right)) {
			if (left.equals(new IntegerType()) || left.equals(new MoneyType())) {
				return new BooleanType();
			}
		}
		
		return new UnknownType();
	}

}