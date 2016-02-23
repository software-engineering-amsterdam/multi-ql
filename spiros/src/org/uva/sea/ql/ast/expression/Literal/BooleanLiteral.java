package org.uva.sea.ql.ast.expression.Literal;

import org.uva.sea.ql.ast.expression.ExpressionVisitor;
import org.uva.sea.ql.ast.node.CodeFragment;
import org.uva.sea.ql.ast.type.BoolType;
import org.uva.sea.ql.ast.type.Type;

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
	public Type getTypeOfExpression() {
		// TODO Auto-generated method stub
		System.out.println("getTypeOfExpression");
		return new BoolType();
	}

	@Override
	public <T> T accept(ExpressionVisitor<T> visitor) {
		// TODO Auto-generated method stub
		return visitor.visit(this);
	}
}

