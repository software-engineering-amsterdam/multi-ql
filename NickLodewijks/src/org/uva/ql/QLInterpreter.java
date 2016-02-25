package org.uva.ql;

import org.uva.ql.ast.ASTNodeVisitorAdapter;
import org.uva.ql.ast.expr.Expr;
import org.uva.ql.ast.expr.LiteralExpr;
import org.uva.ql.ast.expr.VariableExpr;
import org.uva.ql.ast.expr.math.Add;
import org.uva.ql.ast.expr.math.Div;
import org.uva.ql.ast.expr.math.Mul;
import org.uva.ql.ast.expr.math.Neg;
import org.uva.ql.ast.expr.math.Pos;
import org.uva.ql.ast.expr.math.Sub;
import org.uva.ql.ast.expr.rel.And;
import org.uva.ql.ast.expr.rel.Equals;
import org.uva.ql.ast.expr.rel.GreaterThanOrEquals;
import org.uva.ql.ast.expr.rel.GreaterThan;
import org.uva.ql.ast.expr.rel.LessThanOrEquals;
import org.uva.ql.ast.expr.rel.LessThan;
import org.uva.ql.ast.expr.rel.Not;
import org.uva.ql.ast.expr.rel.Or;

public class QLInterpreter extends ASTNodeVisitorAdapter<Object, QLInterpreterContext> {

	@SuppressWarnings("unchecked")
	public static <T> T interpret(Expr expr, QLInterpreterContext context) {
		return (T) expr.accept(new QLInterpreter(), context);
	}

	private QLInterpreter() {

	}

	@Override
	public Object visit(Expr node, QLInterpreterContext context) {
		assert false : "No interpreter implementation for expression type " + node.getClass();
		return null;
	}

	@Override
	public Object visit(Add node, QLInterpreterContext context) {
		return (Integer) node.left().accept(this, context) + (Integer) node.right().accept(this, context);
	}

	@Override
	public Object visit(Sub node, QLInterpreterContext context) {
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
	public Object visit(Div node, QLInterpreterContext context) {
		return (Integer) node.left().accept(this, context) / (Integer) node.right().accept(this, context);
	}

	@Override
	public Object visit(Mul node, QLInterpreterContext context) {
		return (Integer) node.left().accept(this, context) * (Integer) node.right().accept(this, context);
	}

	@Override
	public Object visit(Equals node, QLInterpreterContext context) {
		return node.left().accept(this, context).equals(node.right().accept(this, context));
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
	public Object visit(Pos node, QLInterpreterContext context) {
		return Math.abs((Integer) node.expr().accept(this, context));
	}

	@Override
	public Object visit(Neg node, QLInterpreterContext context) {
		return -Math.abs((Integer) node.expr().accept(this, context));
	}

	@Override
	public Object visit(LiteralExpr node, QLInterpreterContext context) {
		return node.getLiteral().getValue();
	}

	@Override
	public Object visit(VariableExpr node, QLInterpreterContext context) {
		return context.getValue(node.getVariableId());
	}
}
