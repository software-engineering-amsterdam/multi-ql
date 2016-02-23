package org.uva.sea.ql.ast.expression.Literal;

import org.uva.sea.ql.ast.expression.ExpressionVisitor;
import org.uva.sea.ql.ast.node.ASTNode;
import org.uva.sea.ql.ast.node.CodeFragment;
import org.uva.sea.ql.ast.type.StrType;
import org.uva.sea.ql.ast.type.Type;

public class StringLiteral extends Literal {

	// StrValue instead of Str? but why...?
	
	private final String value;
	
	
	public StringLiteral(CodeFragment fragment, String value) {
		super(fragment);
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
	
	@Override
	public <T> T accept(ExpressionVisitor<T> visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public String toString() {
		return this.value;
	}

	@Override
	public Type getTypeOfExpression() {
		// TODO Auto-generated method stub
		return new StrType();
	}
}
