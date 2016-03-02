package nl.nicasso.ql.ast.expression.additive;

import nl.nicasso.ql.ast.CodeLocation;
import nl.nicasso.ql.ast.expression.Polynomial;
import nl.nicasso.ql.ast.type.IntegerType;
import nl.nicasso.ql.ast.type.MoneyType;
import nl.nicasso.ql.ast.type.Type;

public abstract class Additive extends Polynomial {

	public Additive(CodeLocation location) {
		super(location);
	}

	public Type checkAllowedTypes(Type left, Type right) {
		
		if (left.getType().equals(right.getType())) {
			if (left.getType().equals(new IntegerType().getType()) || left.getType().equals(new MoneyType().getType())) {
				return left;
			}
		}
		
		return null;		
	}

}