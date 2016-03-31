package nl.nicasso.ql.ast.nodes.expressions.multiplicative;

import nl.nicasso.ql.ast.nodes.CodeLocation;
import nl.nicasso.ql.ast.nodes.expressions.Binary;
import nl.nicasso.ql.ast.nodes.types.IntegerType;
import nl.nicasso.ql.ast.nodes.types.Type;
import nl.nicasso.ql.ast.nodes.types.UnknownType;

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