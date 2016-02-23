package org.uva.sea.ql.ast.expression.Parenthesis;

import org.uva.sea.ql.ast.expression.Expression;
import org.uva.sea.ql.ast.expression.ExpressionVisitor;
import org.uva.sea.ql.ast.node.ASTNode;
import org.uva.sea.ql.ast.node.CodeFragment;
import org.uva.sea.ql.ast.type.Type;


// class to be removed???

public class Parenthesis extends Expression{
	
	private final Expression expression;

	public Parenthesis(CodeFragment fragment,Expression expression) {
		super(fragment);
		this.expression = expression;
	}

	public Expression getExpression() {
		return this.expression;
	}


	@Override
	public Type getTypeOfExpression() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T accept(ExpressionVisitor<T> visitor) {
		// TODO Auto-generated method stub
		return visitor.visit(this);
	}

}