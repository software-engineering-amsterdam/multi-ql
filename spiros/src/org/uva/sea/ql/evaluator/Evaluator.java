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
		Value left = node.getLeftExpression().accept(this);
		Value right = node.getRightExpression().accept(this);
		return left.equal(right);
	}

	@Override
	public Value visit(NotEqual node) {
		Value left = node.getLeftExpression().accept(this);
		Value right = node.getRightExpression().accept(this);
		return left.notEqual(right);
	}

	@Override
	public Value visit(Greater node) {
		Value left = node.getLeftExpression().accept(this);
		Value right = node.getRightExpression().accept(this);
		return left.greater(right);
	}

	@Override
	public Value visit(GreaterOrEqual node) {
		Value left = node.getLeftExpression().accept(this);
		Value right = node.getRightExpression().accept(this);
		return left.greaterOrEqual(right);
	}

	@Override
	public Value visit(Less node) {
		Value left = node.getLeftExpression().accept(this);
		Value right = node.getRightExpression().accept(this);
		return left.less(right);
	}

	@Override
	public Value visit(LessOrEqual node) {
		Value left = node.getLeftExpression().accept(this);
		Value right = node.getRightExpression().accept(this);
		return left.lessOrEqual(right);
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
		Value left = node.getLeftExpression().accept(this);
		Value right = node.getRightExpression().accept(this);
		return left.and(right);
	}

	@Override
	public Value visit(Or node) {
		Value left = node.getLeftExpression().accept(this);
		Value right = node.getRightExpression().accept(this);
		return left.or(right);
	}

	@Override
	public Value visit(Add node) {
		Value left = node.getLeftExpression().accept(this);
		Value right = node.getRightExpression().accept(this);
		return left.add(right);
	}

	@Override
	public Value visit(Sub node) {
		Value left = node.getLeftExpression().accept(this);
		Value right = node.getRightExpression().accept(this);
		return left.sub(right);
	}

	@Override
	public Value visit(Mul node) {
		Value left = node.getLeftExpression().accept(this);
		Value right = node.getRightExpression().accept(this);
		return left.mul(right);
	}
	
	@Override
	public Value visit(Div node) {
		Value left = node.getLeftExpression().accept(this);
		Value right = node.getRightExpression().accept(this);
		return left.div(right);
	}

	@Override
	public Value visit(Parenthesis node) {
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
