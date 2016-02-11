package org.uva.ql.ast.literal;

import org.uva.ql.ast.ASTNodeVisitor;
import org.uva.ql.ast.ValueType;

public class IntegerLiteral extends Literal<Integer> {

	public IntegerLiteral(Integer value) {
		super(ValueType.INTEGER, value);
	}

	@Override
	public <T, U> T accept(ASTNodeVisitor<T, U> visitor, U context){
		return visitor.visit(this, context);
	}
}
