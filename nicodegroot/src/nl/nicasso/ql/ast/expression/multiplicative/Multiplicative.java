package nl.nicasso.ql.ast.expression.multiplicative;

import nl.nicasso.ql.ast.CodeLocation;
import nl.nicasso.ql.ast.expression.Polynomial;
import nl.nicasso.ql.ast.type.IntegerType;
import nl.nicasso.ql.ast.type.Type;

public abstract class Multiplicative extends Polynomial {

	public Multiplicative(CodeLocation location) {
		super(location);
	}

	public Type checkAllowedTypes(Type left, Type right) {
		
		if (left.equals(right)) {
			if (left.equals(new IntegerType())) {
				return left;
			}
		}
	
		return null;		
	}

}