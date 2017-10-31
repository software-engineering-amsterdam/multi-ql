package ql2.ast.literal;

import ql2.BaseVisitor;

public class IntegerLiteral extends Literal<Integer> {

	public IntegerLiteral(Integer value) {
		super(value);
	}

	@Override
	public <T> T accept(BaseVisitor<T> visitor) {
		return visitor.visit(this);
	}


}
