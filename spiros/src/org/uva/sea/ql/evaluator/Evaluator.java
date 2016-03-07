package org.uva.sea.ql.evaluator;

import org.uva.sea.ql.ast.expression.ExpressionVisitor;
import org.uva.sea.ql.ast.expression.Comparison.Equal;
import org.uva.sea.ql.ast.expression.Comparison.Greater;
import org.uva.sea.ql.ast.expression.Comparison.GreaterOrEqual;
import org.uva.sea.ql.ast.expression.Comparison.Less;
import org.uva.sea.ql.ast.expression.Comparison.LessOrEqual;
import org.uva.sea.ql.ast.expression.Comparison.NotEqual;
import org.uva.sea.ql.ast.expression.Literal.BooleanLiteral;
import org.uva.sea.ql.ast.expression.Literal.Identifier;
import org.uva.sea.ql.ast.expression.Literal.IntegerLiteral;
import org.uva.sea.ql.ast.expression.Literal.StringLiteral;
import org.uva.sea.ql.ast.expression.Logical.And;
import org.uva.sea.ql.ast.expression.Logical.Or;
import org.uva.sea.ql.ast.expression.Numerical.Add;
import org.uva.sea.ql.ast.expression.Numerical.Div;
import org.uva.sea.ql.ast.expression.Numerical.Mul;
import org.uva.sea.ql.ast.expression.Numerical.Sub;
import org.uva.sea.ql.ast.expression.Parenthesis.Parenthesis;
import org.uva.sea.ql.ast.expression.Unary.Negative;
import org.uva.sea.ql.ast.expression.Unary.Not;
import org.uva.sea.ql.ast.expression.Unary.Positive;

public class Evaluator implements ExpressionVisitor<Value>  {

	@Override
	public Value visit(Equal node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value visit(NotEqual node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value visit(Greater node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value visit(GreaterOrEqual node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value visit(Less node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value visit(LessOrEqual node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value visit(BooleanLiteral node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value visit(Identifier node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value visit(IntegerLiteral node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value visit(StringLiteral node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value visit(And node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value visit(Or node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value visit(Add node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value visit(Sub node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value visit(Mul node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value visit(Div node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value visit(Parenthesis node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value visit(Not node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value visit(Positive node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value visit(Negative node) {
		// TODO Auto-generated method stub
		return null;
	}

}
