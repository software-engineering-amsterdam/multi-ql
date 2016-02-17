package org.uva.sea.ql.ast.expr.literal;

import org.uva.sea.ql.ast.ValueType;

public class StringLiteral extends Literal<String> {

	public StringLiteral(String value) {
		super(ValueType.STRING, value);
		// TODO Auto-generated constructor stub
	}

}
