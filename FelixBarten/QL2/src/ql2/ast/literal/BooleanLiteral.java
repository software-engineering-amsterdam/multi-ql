package ql2.ast.literal;

import ql2.BaseVisitor;

public class BooleanLiteral extends Literal<Boolean> {

	public BooleanLiteral(Boolean value) {
		super(value);
		// TODO Auto-generated constructor stub
	}
	@Override
	public <T> T accept(BaseVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
