package org.uva.sea.ql.ast.expr.literal;

import org.uva.sea.ql.ast.expr.type.StringType;

public class StringLiteral extends Literal<String> {

	public StringLiteral(String value) {
		super(new StringType(), value);
		// TODO Auto-generated constructor stub
	}

}
