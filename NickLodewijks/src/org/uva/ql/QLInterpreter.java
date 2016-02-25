package org.uva.ql;

import org.uva.ql.ast.ASTNodeVisitorAdapter;
import org.uva.ql.ast.expr.Context;
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
import org.uva.ql.ast.expr.rel.Eq;
import org.uva.ql.ast.expr.rel.GEq;
import org.uva.ql.ast.expr.rel.GT;
import org.uva.ql.ast.expr.rel.LEq;
import org.uva.ql.ast.expr.rel.LT;
import org.uva.ql.ast.expr.rel.Not;
import org.uva.ql.ast.expr.rel.Or;

public class QLInterpreter extends ASTNodeVisitorAdapter<Object, Context> {

	@SuppressWarnings("unchecked")
	public static <T> T interpret(Expr expr, Context context) {
		return (T) expr.accept(new QLInterpreter(), context);
	}

	private QLInterpreter() {

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
