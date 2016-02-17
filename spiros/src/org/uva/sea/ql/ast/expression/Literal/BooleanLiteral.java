package org.uva.sea.ql.ast.expression.Literal;

import org.uva.sea.ql.ast.expression.ExpressionVisitor;
import org.uva.sea.ql.ast.node.ASTNode;
import org.uva.sea.ql.ast.node.CodeFragment;

public class BooleanLiteral extends Literal {
	
	private final Boolean value;

	public BooleanLiteral(CodeFragment fragment, Boolean value) {
		super(fragment);
		this.value = value;
	}

	public Boolean getValue() {
		return this.value;
	}	
	
	
	@Override
	public ASTNode accept(ExpressionVisitor<ASTNode> visitor) {
		return visitor.visit(this);
	}
}

