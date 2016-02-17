package org.uva.sea.ql.ast.expression.Literal;

import org.uva.sea.ql.ast.expression.ExpressionVisitor;
import org.uva.sea.ql.ast.node.ASTNode;
import org.uva.sea.ql.ast.node.CodeFragment;

public class Identifier extends Literal {
	
	private final String id;
	
	public Identifier(CodeFragment fragment, String id) {
		super(fragment);
		this.id = id;
	}

	public String getValue() {
		return this.id;
	}
	
	@Override
	public ASTNode accept(ExpressionVisitor<ASTNode> visitor) {
		return visitor.visit(this);
	}

}