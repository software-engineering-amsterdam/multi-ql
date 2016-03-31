package nl.nicasso.ql.ast.nodes.expressions.relational;

import nl.nicasso.ql.ast.nodes.CodeLocation;
import nl.nicasso.ql.ast.nodes.expressions.Binary;
import nl.nicasso.ql.ast.nodes.types.BooleanType;
import nl.nicasso.ql.ast.nodes.types.IntegerType;
import nl.nicasso.ql.ast.nodes.types.MoneyType;
import nl.nicasso.ql.ast.nodes.types.Type;
import nl.nicasso.ql.ast.nodes.types.UnknownType;

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