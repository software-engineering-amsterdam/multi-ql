package org.uva.sea.ql.ast.expression.Logical;

import org.uva.sea.ql.ast.expression.Expression;
import org.uva.sea.ql.ast.node.CodeFragment;
import org.uva.sea.ql.ast.type.BoolType;
import org.uva.sea.ql.ast.type.Type;

public abstract class Binary extends Expression {
	
	private final Expression rightExpression;
	private final Expression leftExpression;
	
	
	public Binary(CodeFragment fragment, Expression rightExpression, Expression leftExpression) {
		super(fragment);
		this.rightExpression = rightExpression;
		this.leftExpression = leftExpression;
	}


	public Expression getRightExpression() {
		return rightExpression;
	}


	public Expression getLeftExpression() {
		return leftExpression;
	}
	
	public Type getTypeOfExpression() {
		return new BoolType();
	}
	

}
