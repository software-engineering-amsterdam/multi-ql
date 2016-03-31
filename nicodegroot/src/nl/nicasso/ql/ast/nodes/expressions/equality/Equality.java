package nl.nicasso.ql.ast.nodes.expressions.equality;

import nl.nicasso.ql.ast.nodes.CodeLocation;
import nl.nicasso.ql.ast.nodes.expressions.Binary;
import nl.nicasso.ql.ast.nodes.types.BooleanType;
import nl.nicasso.ql.ast.nodes.types.Type;
import nl.nicasso.ql.ast.nodes.types.UnknownType;

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
