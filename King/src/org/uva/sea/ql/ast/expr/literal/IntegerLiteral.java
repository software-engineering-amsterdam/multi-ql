package org.uva.sea.ql.ast.expr.literal;

import org.uva.sea.ql.ast.expr.type.IntegerType;

public class IntegerLiteral extends Literal<Integer> {

	public IntegerLiteral(Integer value) {
		super(new IntegerType(), value);
	}

}
