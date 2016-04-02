package eu.bankersen.kevin.ql.form.ast.expressions.visitors;

import eu.bankersen.kevin.ql.form.ast.expressions.Binary;
import eu.bankersen.kevin.ql.form.ast.expressions.logic.And;
import eu.bankersen.kevin.ql.form.ast.expressions.logic.Eq;
import eu.bankersen.kevin.ql.form.ast.expressions.logic.GEq;
import eu.bankersen.kevin.ql.form.ast.expressions.logic.GT;
import eu.bankersen.kevin.ql.form.ast.expressions.logic.LEq;
import eu.bankersen.kevin.ql.form.ast.expressions.logic.LT;
import eu.bankersen.kevin.ql.form.ast.expressions.logic.NEq;
import eu.bankersen.kevin.ql.form.ast.expressions.logic.Not;
import eu.bankersen.kevin.ql.form.ast.expressions.logic.Or;
import eu.bankersen.kevin.ql.form.ast.expressions.math.Add;
import eu.bankersen.kevin.ql.form.ast.expressions.math.Div;
import eu.bankersen.kevin.ql.form.ast.expressions.math.Mul;
import eu.bankersen.kevin.ql.form.ast.expressions.math.Neg;
import eu.bankersen.kevin.ql.form.ast.expressions.math.Pos;
import eu.bankersen.kevin.ql.form.ast.expressions.math.Sub;

public abstract class LeafVisitor<T, U> implements Visitor<T, U> {

	public abstract T visitBinary(Binary expression, U context);

	@Override
	public T visit(Sub expression, U context) {
		return visitBinary(expression, context);
	}

	@Override
	public T visit(Add expression, U context) {
		return visitBinary(expression, context);
	}

	@Override
	public T visit(Div expression, U context) {
		return visitBinary(expression, context);
	}

	@Override
	public T visit(Mul expression, U context) {
		return visitBinary(expression, context);
	}

	@Override
	public T visit(Pos expression, U context) {
		return expression.expr().accept(this, context);
	}

	@Override
	public T visit(Neg expression, U context) {
		return expression.expr().accept(this, context);
	}

	@Override
	public T visit(Or expression, U context) {
		return visitBinary(expression, context);
	}

	@Override
	public T visit(And expression, U context) {
		return visitBinary(expression, context);
	}

	@Override
	public T visit(Eq expression, U context) {
		return visitBinary(expression, context);
	}

	@Override
	public T visit(GEq expression, U context) {
		return visitBinary(expression, context);
	}

	@Override
	public T visit(GT expression, U context) {
		return visitBinary(expression, context);
	}

	@Override
	public T visit(LEq expression, U context) {
		return visitBinary(expression, context);
	}

	@Override
	public T visit(LT expression, U context) {
		return visitBinary(expression, context);
	}

	@Override
	public T visit(NEq expression, U context) {
		return visitBinary(expression, context);
	}

	@Override
	public T visit(Not expression, U context) {
		return expression.expr().accept(this, context);
	}
}
