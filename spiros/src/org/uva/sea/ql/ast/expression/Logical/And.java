package org.uva.sea.ql.ast.expression.Logical;

import org.uva.sea.ql.ast.expression.Expression;
import org.uva.sea.ql.ast.expression.ExpressionVisitor;
import org.uva.sea.ql.ast.node.ASTNode;
import org.uva.sea.ql.ast.node.CodeFragment;

public class And extends Binary {

	public And(CodeFragment fragment, Expression rightExpression,
			Expression leftExpression) {
		super(fragment, rightExpression, leftExpression);
	}
	

	@Override
	public <T> T accept(ExpressionVisitor<T> visitor) {
		// TODO Auto-generated method stub
		return visitor.visit(this);
	}
	
	
}
