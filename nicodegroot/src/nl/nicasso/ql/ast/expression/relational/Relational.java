package nl.nicasso.ql.ast.expression.relational;

import nl.nicasso.ql.ast.CodeLocation;
import nl.nicasso.ql.ast.expression.Polynomial;
import nl.nicasso.ql.ast.type.IntegerType;
import nl.nicasso.ql.ast.type.MoneyType;
import nl.nicasso.ql.ast.type.Type;

public abstract class Relational extends Polynomial {

	public Relational(CodeLocation location) {
		super(location);
	}

	public Type checkAllowedTypes(Type left, Type right) {
		if (left.equals(right)) {
			if (left.equals(new IntegerType()) || left.equals(new MoneyType())) {
				return left;
			}
		}
		
		return null;
	}

}