package org.uva.ql.ast.literal;

import org.uva.ql.ast.ValueType;

public class StringLiteral extends Literal<String> {

	public StringLiteral(String value) {
		super(ValueType.STRING, value);
	}
}
