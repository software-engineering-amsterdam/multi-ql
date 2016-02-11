package org.uva.ql.ast.literal;

import org.uva.ql.ast.ASTNodeVisitor;
import org.uva.ql.ast.ValueType;

public class BooleanLiteral extends Literal<Boolean> {

	public BooleanLiteral(boolean value) {
		super(ValueType.BOOLEAN, value);
	}

	@Override
	public <T, U> T accept(ASTNodeVisitor<T, U> visitor, U context){
		return visitor.visit(this, context);
	}
}
