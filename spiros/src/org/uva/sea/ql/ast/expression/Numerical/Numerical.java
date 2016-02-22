package org.uva.sea.ql.ast.expression.Numerical;

import org.uva.sea.ql.ast.expression.Expression;
import org.uva.sea.ql.ast.expression.ExpressionVisitor;
import org.uva.sea.ql.ast.expression.Logical.Binary;
import org.uva.sea.ql.ast.node.ASTNode;
import org.uva.sea.ql.ast.node.CodeFragment;
import org.uva.sea.ql.ast.type.IntType;
import org.uva.sea.ql.ast.type.Type;

public abstract class Numerical extends Binary {

	public Numerical(CodeFragment fragment, Expression rightExpression,
			Expression leftExpression) {
		super(fragment, rightExpression, leftExpression);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ASTNode accept(ExpressionVisitor<ASTNode> visitor) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Type getTypeOfExpression() {
		return new IntType();
	}

}
