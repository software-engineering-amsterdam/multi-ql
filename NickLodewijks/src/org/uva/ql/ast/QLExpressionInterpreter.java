package org.uva.ql.ast;

import org.uva.ql.ast.expr.Add;
import org.uva.ql.ast.expr.And;
import org.uva.ql.ast.expr.Context;
import org.uva.ql.ast.expr.Div;
import org.uva.ql.ast.expr.Eq;
import org.uva.ql.ast.expr.Expr;
import org.uva.ql.ast.expr.GEq;
import org.uva.ql.ast.expr.GT;
import org.uva.ql.ast.expr.LEq;
import org.uva.ql.ast.expr.LT;
import org.uva.ql.ast.expr.LiteralExpr;
import org.uva.ql.ast.expr.Mul;
import org.uva.ql.ast.expr.Neg;
import org.uva.ql.ast.expr.Not;
import org.uva.ql.ast.expr.Or;
import org.uva.ql.ast.expr.Pos;
import org.uva.ql.ast.expr.Sub;
import org.uva.ql.ast.expr.VariableExpr;

public class QLExpressionInterpreter extends ASTNodeVisitorAdapter<Object, Context> {

	@SuppressWarnings("unchecked")
	public static <T> T interpret(Expr expr, Context context) {
		return (T) expr.accept(new QLExpressionInterpreter(), context);
	}

	private QLExpressionInterpreter() {

	}

	@Override
	public Object visit(Expr node, Context context) {
		assert false : "No interpreter implementation for expression type " + node.getClass();
		return null;
	}

	@Override
	public Object visit(Add node, Context context) {
		return (Integer) node.left().accept(this, context) + (Integer) node.right().accept(this, context);
	}

	@Override
	public Object visit(Sub node, Context context) {
		return (Integer) node.left().accept(this, context) - (Integer) node.right().accept(this, context);
	}

	@Override
	public Object visit(And node, Context context) {
		return (Boolean) node.left().accept(this, context) && (Boolean) node.right().accept(this, context);
	}

	@Override
	public Object visit(Or node, Context context) {
		return (Boolean) node.left().accept(this, context) || (Boolean) node.right().accept(this, context);
	}

	@Override
	public Object visit(Div node, Context context) {
		return (Integer) node.left().accept(this, context) / (Integer) node.right().accept(this, context);
	}

	@Override
	public Object visit(Mul node, Context context) {
		return (Integer) node.left().accept(this, context) * (Integer) node.right().accept(this, context);
	}

	@Override
	public Object visit(Eq node, Context context) {
		return node.left().accept(this, context).equals(node.right().accept(this, context));
	}

	@Override
	public Object visit(GEq node, Context context) {
		return (Integer) node.left().accept(this, context) >= (Integer) node.right().accept(this, context);
	}

	@Override
	public Object visit(GT node, Context context) {
		return (Integer) node.left().accept(this, context) > (Integer) node.right().accept(this, context);
	}

	@Override
	public Object visit(LEq node, Context context) {
		return (Integer) node.left().accept(this, context) <= (Integer) node.right().accept(this, context);
	}

	@Override
	public Object visit(LT node, Context context) {
		return (Integer) node.left().accept(this, context) < (Integer) node.right().accept(this, context);
	}

	@Override
	public Object visit(Not node, Context context) {
		return !(Boolean) node.expr().accept(this, context);
	}

	@Override
	public Object visit(Pos node, Context context) {
		return Math.abs((Integer) node.expr().accept(this, context));
	}

	@Override
	public Object visit(Neg node, Context context) {
		return -Math.abs((Integer) node.expr().accept(this, context));
	}

	@Override
	public Object visit(LiteralExpr node, Context context) {
		return node.getLiteral().getValue();
	}

	@Override
	public Object visit(VariableExpr node, Context context) {
		return context.getValue(node.getVariableId());
	}
}
