package org.uva.sea.ql.ast.expr.literal;

import org.uva.sea.ql.ast.expr.type.BooleanType;

public class BooleanLiteral extends Literal<Boolean> {

	public BooleanLiteral(boolean value) {
		super(new BooleanType(), value);
		// TODO Auto-generated constructor stub
	}

}
