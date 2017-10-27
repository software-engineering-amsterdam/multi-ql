package ql2.ast.literal;

import ql2.BaseVisitor;

public class StringLiteral extends Literal<String> {

	
	public StringLiteral(String value) {
		super(value);
	}
	
	@Override
	public <T> T accept(BaseVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
