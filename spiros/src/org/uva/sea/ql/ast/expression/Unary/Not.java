package org.uva.sea.ql.ast.expression.Unary;

import org.uva.sea.ql.ast.expression.Expression;
import org.uva.sea.ql.ast.expression.ExpressionVisitor;
import org.uva.sea.ql.ast.node.CodeFragment;
import org.uva.sea.ql.ast.type.BoolType;
import org.uva.sea.ql.ast.type.Type;

public class Not extends Unary {

	public Not(CodeFragment fragment, Expression expression) {
		super(fragment, expression);
	}
	

//	@Override
//	public Type getTypeOfExpression() {
//		// TODO Auto-generated method stub
//		return new BoolType();
//	}

	@Override
	public <T> T accept(ExpressionVisitor<T> visitor) {
		// TODO Auto-generated method stub
		return visitor.visit(this);
	}

}