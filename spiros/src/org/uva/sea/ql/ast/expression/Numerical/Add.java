package org.uva.sea.ql.ast.expression.Numerical;

import org.uva.sea.ql.ast.expression.Expression;
import org.uva.sea.ql.ast.expression.ExpressionVisitor;
import org.uva.sea.ql.ast.expression.Logical.Binary;
import org.uva.sea.ql.ast.node.ASTNode;
import org.uva.sea.ql.ast.node.CodeFragment;

public class Add extends Binary {

	public Add(CodeFragment fragment, Expression rightExpression,
			Expression leftExpression) {
		super(fragment, rightExpression, leftExpression);
	}
	
	@Override
	public ASTNode accept(ExpressionVisitor<ASTNode> visitor) {
		return visitor.visit(this);
	}

}