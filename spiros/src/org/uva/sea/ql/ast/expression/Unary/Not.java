package org.uva.sea.ql.ast.expression.Unary;

import org.uva.sea.ql.ast.expression.Expression;
import org.uva.sea.ql.ast.expression.ExpressionVisitor;
import org.uva.sea.ql.ast.node.ASTNode;
import org.uva.sea.ql.ast.node.CodeFragment;

public class Not extends Unary {

	public Not(CodeFragment fragment, Expression expression) {
		super(fragment, expression);
	}
	
	@Override
	public ASTNode accept(ExpressionVisitor<ASTNode> visitor) {
		return visitor.visit(this);
	}

}