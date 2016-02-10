package org.uva.ql.ast.literal;

import org.uva.ql.ast.ASTNodeVisitor;
import org.uva.ql.ast.ValueType;

public class BooleanLiteral extends Literal<Boolean> {

	public BooleanLiteral(boolean value) {
		super(ValueType.BOOLEAN, value);
	}

	@Override
	public void accept(ASTNodeVisitor visitor) {
		visitor.visit(this);
	}
}
