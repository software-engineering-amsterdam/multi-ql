package org.uva.sea.ql.ast.literal;

import org.uva.sea.ql.ast.ValueType;

public class StringLiteral extends Literal<String> {

	public StringLiteral(String value) {
		super(ValueType.STRING, value);
	}
}
