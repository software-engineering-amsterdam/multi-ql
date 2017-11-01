package ql2.ast.literal;

import ql2.BaseVisitor;

public class BooleanLiteral extends Literal<Boolean> {

	public BooleanLiteral(Boolean value) {
		super(value);
	}
	
	@Override
	public <T> T accept(BaseVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
