package nl.nicasso.ql.ast.nodes.expressions.conditional;

import nl.nicasso.ql.ast.nodes.CodeLocation;
import nl.nicasso.ql.ast.nodes.expressions.Binary;
import nl.nicasso.ql.ast.nodes.types.BooleanType;
import nl.nicasso.ql.ast.nodes.types.Type;
import nl.nicasso.ql.ast.nodes.types.UnknownType;

public abstract class Conditional extends Binary {

	public Conditional(CodeLocation location) {
		super(location);
	}

	@Override
	public Type inferType(Type left, Type right) {

		if (left.equals(right)) {
			if (left.equals(new BooleanType())) {
				return new BooleanType();
			}
		}

		return new UnknownType();
	}

}
