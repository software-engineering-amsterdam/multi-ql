package org.uva.ql;

import org.uva.ql.ast.BooleanValue;
import org.uva.ql.ast.NumberValue;
import org.uva.ql.ast.StringValue;
import org.uva.ql.ast.Value;
import org.uva.ql.ast.expr.Add;
import org.uva.ql.ast.expr.And;
import org.uva.ql.ast.expr.BooleanLiteral;
import org.uva.ql.ast.expr.Divide;
import org.uva.ql.ast.expr.Equals;
import org.uva.ql.ast.expr.EqualsNot;
import org.uva.ql.ast.expr.Expr;
import org.uva.ql.ast.expr.ExprVisitor;
import org.uva.ql.ast.expr.GreaterThan;
import org.uva.ql.ast.expr.GreaterThanOrEquals;
import org.uva.ql.ast.expr.IntegerLiteral;
import org.uva.ql.ast.expr.LessThan;
import org.uva.ql.ast.expr.LessThanOrEquals;
import org.uva.ql.ast.expr.Multiply;
import org.uva.ql.ast.expr.Negative;
import org.uva.ql.ast.expr.Not;
import org.uva.ql.ast.expr.Or;
import org.uva.ql.ast.expr.Positive;
import org.uva.ql.ast.expr.StringLiteral;
import org.uva.ql.ast.expr.Subtract;
import org.uva.ql.ast.expr.VariableExpr;

public class QLInterpreter implements ExprVisitor<Value, QLInterpreterContext> {

	@SuppressWarnings("unchecked")
	public static <T> T interpret(Expr expr, QLInterpreterContext context) {
		try {
			return (T) expr.accept(new QLInterpreter(), context);
		} catch (RuntimeException ex) {
			throw new RuntimeException(String.format("Failed to interpret expression '%s'", expr.getSourceText()), ex);
		}
	}

	private QLInterpreter() {

	}

	@Override
	public Value visit(Add node, QLInterpreterContext context) {
		return node.left().accept(this, context).add(node.right().accept(this, context));
	}

	@Override
	public Value visit(Subtract node, QLInterpreterContext context) {
		return node.left().accept(this, context).subtract(node.right().accept(this, context));
	}

	@Override
	public Value visit(And node, QLInterpreterContext context) {
		return node.left().accept(this, context).and(node.right().accept(this, context));
	}

	@Override
	public Value visit(Or node, QLInterpreterContext context) {
		return node.left().accept(this, context).or(node.right().accept(this, context));
	}

	@Override
	public Value visit(Divide node, QLInterpreterContext context) {
		return node.left().accept(this, context).div(node.right().accept(this, context));
	}

	@Override
	public Value visit(Multiply node, QLInterpreterContext context) {
		return node.left().accept(this, context).mul(node.right().accept(this, context));
	}

	@Override
	public Value visit(Equals node, QLInterpreterContext context) {
		return node.left().accept(this, context).equal(node.right().accept(this, context));
	}

	@Override
	public Value visit(EqualsNot node, QLInterpreterContext context) {
		return node.left().accept(this, context).equal(node.right().accept(this, context)).not();
	}

	@Override
	public Value visit(GreaterThanOrEquals node, QLInterpreterContext context) {
		return node.left().accept(this, context).greaterThanOrEqual(node.right().accept(this, context));
	}

	@Override
	public Value visit(GreaterThan node, QLInterpreterContext context) {
		return node.left().accept(this, context).greaterThan(node.right().accept(this, context));
	}

	@Override
	public BooleanValue visit(LessThanOrEquals node, QLInterpreterContext context) {
		return node.left().accept(this, context).lessThanOrEqual(node.right().accept(this, context));
	}

	@Override
	public BooleanValue visit(LessThan node, QLInterpreterContext context) {
		return node.left().accept(this, context).lessThan(node.right().accept(this, context));
	}

	@Override
	public BooleanValue visit(Not node, QLInterpreterContext context) {
		return node.expr().accept(this, context).not();
	}

	@Override
	public Value visit(Positive node, QLInterpreterContext context) {
		return node.expr().accept(this, context).positive();
	}

	@Override
	public Value visit(Negative node, QLInterpreterContext context) {
		return node.expr().accept(this, context).negative();
	}

	@Override
	public Value visit(VariableExpr node, QLInterpreterContext context) {
		return context.getValue(node.getVariableId());
	}

	@Override
	public BooleanValue visit(BooleanLiteral node, QLInterpreterContext context) {
		return node.getValue();
	}

	@Override
	public NumberValue visit(IntegerLiteral node, QLInterpreterContext context) {
		return node.getValue();
	}

	@Override
	public StringValue visit(StringLiteral node, QLInterpreterContext context) {
		return node.getValue();
	}
}
