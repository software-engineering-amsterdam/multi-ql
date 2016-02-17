package org.uva.sea.ql.ast.expression;

import org.uva.sea.ql.ast.node.ASTNode;
import org.uva.sea.ql.ast.node.CodeFragment;


public abstract class Expression extends ASTNode {
	
	public Expression(CodeFragment fragment) {
		super(fragment);
	}
	
	public abstract ASTNode accept(ExpressionVisitor<ASTNode> visitor);
	
}