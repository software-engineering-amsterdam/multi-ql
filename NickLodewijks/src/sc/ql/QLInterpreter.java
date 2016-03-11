package sc.ql;

import sc.ql.ast.expr.ExprVisitor;
import sc.ql.ast.expr.Expression;
import sc.ql.ast.expr.Expression.Add;
import sc.ql.ast.expr.Expression.And;
import sc.ql.ast.expr.Expression.BooleanLiteral;
import sc.ql.ast.expr.Expression.Divide;
import sc.ql.ast.expr.Expression.Equals;
import sc.ql.ast.expr.Expression.EqualsNot;
import sc.ql.ast.expr.Expression.GreaterThan;
import sc.ql.ast.expr.Expression.GreaterThanOrEqual;
import sc.ql.ast.expr.Expression.IntegerLiteral;
import sc.ql.ast.expr.Expression.LessThan;
import sc.ql.ast.expr.Expression.LessThanOrEqual;
import sc.ql.ast.expr.Expression.Multiply;
import sc.ql.ast.expr.Expression.Negative;
import sc.ql.ast.expr.Expression.Not;
import sc.ql.ast.expr.Expression.Or;
import sc.ql.ast.expr.Expression.Positive;
import sc.ql.ast.expr.Expression.StringLiteral;
import sc.ql.ast.expr.Expression.Subtract;
import sc.ql.ast.expr.Expression.VariableExpr;
import sc.ql.ast.value.BooleanValue;
import sc.ql.ast.value.NumberValue;
import sc.ql.ast.value.StringValue;
import sc.ql.ast.value.Value;

public class QLInterpreter implements ExprVisitor<Value, QLContext> {

	@SuppressWarnings("unchecked")
	public static <T extends Value> T interpret(Expression expr, QLContext context) {
		try {
			return (T) expr.accept(new QLInterpreter(), context);
		} catch (RuntimeException ex) {
			throw new RuntimeException(String.format("Failed to interpret expression '%s'", expr.getSourceText()), ex);
		}
	}

	private QLInterpreter() {

	}

	@Override
	public Value visit(Add node, QLContext context) {
		return node.left().accept(this, context).add(node.right().accept(this, context));
	}

	@Override
	public Value visit(Subtract node, QLContext context) {
		return node.left().accept(this, context).subtract(node.right().accept(this, context));
	}

	@Override
	public Value visit(And node, QLContext context) {
		return node.left().accept(this, context).and(node.right().accept(this, context));
	}

	@Override
	public Value visit(Or node, QLContext context) {
		return node.left().accept(this, context).or(node.right().accept(this, context));
	}

	@Override
	public Value visit(Divide node, QLContext context) {
		return node.left().accept(this, context).div(node.right().accept(this, context));
	}

	@Override
	public Value visit(Multiply node, QLContext context) {
		return node.left().accept(this, context).mul(node.right().accept(this, context));
	}

	@Override
	public Value visit(Equals node, QLContext context) {
		return node.left().accept(this, context).equal(node.right().accept(this, context));
	}

	@Override
	public Value visit(EqualsNot node, QLContext context) {
		return node.left().accept(this, context).equal(node.right().accept(this, context)).not();
	}

	@Override
	public Value visit(GreaterThanOrEqual node, QLContext context) {
		return node.left().accept(this, context).greaterThanOrEqual(node.right().accept(this, context));
	}

	@Override
	public Value visit(GreaterThan node, QLContext context) {
		return node.left().accept(this, context).greaterThan(node.right().accept(this, context));
	}

	@Override
	public BooleanValue visit(LessThanOrEqual node, QLContext context) {
		return node.left().accept(this, context).lessThanOrEqual(node.right().accept(this, context));
	}

	@Override
	public BooleanValue visit(LessThan node, QLContext context) {
		return node.left().accept(this, context).lessThan(node.right().accept(this, context));
	}

	@Override
	public BooleanValue visit(Not node, QLContext context) {
		return node.expr().accept(this, context).not();
	}

	@Override
	public Value visit(Positive node, QLContext context) {
		return node.expr().accept(this, context).positive();
	}

	@Override
	public Value visit(Negative node, QLContext context) {
		return node.expr().accept(this, context).negative();
	}

	@Override
	public Value visit(VariableExpr node, QLContext context) {
		return context.getValue(node.getVariableId());
	}

	@Override
	public BooleanValue visit(BooleanLiteral node, QLContext context) {
		return node.getValue();
	}

	@Override
	public NumberValue visit(IntegerLiteral node, QLContext context) {
		return node.getValue();
	}

	@Override
	public StringValue visit(StringLiteral node, QLContext context) {
		return node.getValue();
	}
}
