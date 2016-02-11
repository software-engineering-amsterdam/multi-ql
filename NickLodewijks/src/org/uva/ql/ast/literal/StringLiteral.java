package org.uva.ql.ast.literal;

import org.uva.ql.ast.ASTNodeVisitor;
import org.uva.ql.ast.ValueType;

public class StringLiteral extends Literal<String> {

	public StringLiteral(String value) {
		super(ValueType.STRING, value);
	}

	@Override
	public <T, U> T accept(ASTNodeVisitor<T, U> visitor, U context){
		return visitor.visit(this, context);
	}
}
