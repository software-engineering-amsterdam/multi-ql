package org.uva.sea.ql.ast.expression.Literal;

import org.uva.sea.ql.ast.expression.ExpressionVisitor;
import org.uva.sea.ql.ast.node.ASTNode;
import org.uva.sea.ql.ast.node.CodeFragment;

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
	public ASTNode accept(ExpressionVisitor<ASTNode> visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public String toString() {
		return this.value;
	}
}
