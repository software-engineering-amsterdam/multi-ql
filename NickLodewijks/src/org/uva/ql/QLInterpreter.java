package org.uva.ql;

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

public class QLInterpreter implements ExprVisitor<Object, QLInterpreterContext> {

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
	public Object visit(Add node, QLInterpreterContext context) {
		return (Integer) node.left().accept(this, context) + (Integer) node.right().accept(this, context);
	}

	@Override
	public Object visit(Subtract node, QLInterpreterContext context) {
		return (Integer) node.left().accept(this, context) - (Integer) node.right().accept(this, context);
	}

	@Override
	public Object visit(And node, QLInterpreterContext context) {
		return (Boolean) node.left().accept(this, context) && (Boolean) node.right().accept(this, context);
	}

	@Override
	public Object visit(Or node, QLInterpreterContext context) {
		return (Boolean) node.left().accept(this, context) || (Boolean) node.right().accept(this, context);
	}

	@Override
	public Object visit(Divide node, QLInterpreterContext context) {
		return (Integer) node.left().accept(this, context) / (Integer) node.right().accept(this, context);
	}

	@Override
	public Object visit(Multiply node, QLInterpreterContext context) {
		return (Integer) node.left().accept(this, context) * (Integer) node.right().accept(this, context);
	}

	@Override
	public Object visit(Equals node, QLInterpreterContext context) {
		return node.left().accept(this, context).equals(node.right().accept(this, context));
	}

	@Override
	public Object visit(EqualsNot node, QLInterpreterContext context) {
		return !node.left().accept(this, context).equals(node.right().accept(this, context));
	}

	@Override
	public Object visit(GreaterThanOrEquals node, QLInterpreterContext context) {
		return (Integer) node.left().accept(this, context) >= (Integer) node.right().accept(this, context);
	}

	@Override
	public Object visit(GreaterThan node, QLInterpreterContext context) {
		return (Integer) node.left().accept(this, context) > (Integer) node.right().accept(this, context);
	}

	@Override
	public Object visit(LessThanOrEquals node, QLInterpreterContext context) {
		return (Integer) node.left().accept(this, context) <= (Integer) node.right().accept(this, context);
	}

	@Override
	public Object visit(LessThan node, QLInterpreterContext context) {
		return (Integer) node.left().accept(this, context) < (Integer) node.right().accept(this, context);
	}

	@Override
	public Object visit(Not node, QLInterpreterContext context) {
		return !(Boolean) node.expr().accept(this, context);
	}

	@Override
	public Object visit(Positive node, QLInterpreterContext context) {
		return Math.abs((Integer) node.expr().accept(this, context));
	}

	@Override
	public Object visit(Negative node, QLInterpreterContext context) {
		return -Math.abs((Integer) node.expr().accept(this, context));
	}

	@Override
	public Object visit(VariableExpr node, QLInterpreterContext context) {
		return context.getValue(node.getVariableId());
	}

	@Override
	public Object visit(BooleanLiteral node, QLInterpreterContext context) {
		return node.getValue();
	}

	@Override
	public Object visit(IntegerLiteral node, QLInterpreterContext context) {
		return node.getValue();
	}

	@Override
	public Object visit(StringLiteral node, QLInterpreterContext context) {
		return node.getValue();
	}
}
