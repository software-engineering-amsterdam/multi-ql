package ql2.ast.literal;

import ql2.BaseVisitor;

public class StringLiteral extends Literal<String> {
	
	public StringLiteral(String value) {
		super(stripQuotation(value));
	}
	
	private static String stripQuotation(String value) {
		if (value.charAt(0) == '"' && value.charAt(value.length()-1) == '"') {
			return value.substring(1, value.length()-1);
		}
		return value;
	}

	@Override
	public <T> T accept(BaseVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
