package org.uva.sea.ql.ast.expression.Unary;

import org.uva.sea.ql.ast.expression.Expression;
import org.uva.sea.ql.ast.expression.ExpressionVisitor;
import org.uva.sea.ql.ast.node.ASTNode;
import org.uva.sea.ql.ast.node.CodeFragment;
import org.uva.sea.ql.ast.type.IntType;
import org.uva.sea.ql.ast.type.Type;

public class Negative extends Unary {

	public Negative(CodeFragment fragment, Expression expression) {
		super(fragment, expression);
	}

	@Override
	public ASTNode accept(ExpressionVisitor<ASTNode> visitor) {
		return visitor.visit(this);
	}

	@Override
	public Type getTypeOfExpression() {
		// TODO Auto-generated method stub
		return new IntType();
	}
}
