package eu.bankersen.kevin.ql.ast.expr;

import eu.bankersen.kevin.ql.ast.expr.logic.And;
import eu.bankersen.kevin.ql.ast.expr.logic.Eq;
import eu.bankersen.kevin.ql.ast.expr.logic.GEq;
import eu.bankersen.kevin.ql.ast.expr.logic.GT;
import eu.bankersen.kevin.ql.ast.expr.logic.LEq;
import eu.bankersen.kevin.ql.ast.expr.logic.LT;
import eu.bankersen.kevin.ql.ast.expr.logic.NEq;
import eu.bankersen.kevin.ql.ast.expr.logic.Not;
import eu.bankersen.kevin.ql.ast.expr.logic.Or;
import eu.bankersen.kevin.ql.ast.expr.math.Add;
import eu.bankersen.kevin.ql.ast.expr.math.Div;
import eu.bankersen.kevin.ql.ast.expr.math.Mul;
import eu.bankersen.kevin.ql.ast.expr.math.Neg;
import eu.bankersen.kevin.ql.ast.expr.math.Pos;
import eu.bankersen.kevin.ql.ast.expr.math.Sub;

public abstract class IdentifierVisitor<T, U> implements ExprVisitor<T, U> {

    @Override
    public T visit(Sub o, U context) {
	return null;
    }

    @Override
    public T visit(Add o, U context) {
	return null;
    }

    @Override
    public T visit(Div o, U context) {
	return null;
    }

    @Override
    public T visit(Mul o, U context) {
	return null;
    }

    @Override
    public T visit(Pos o, U context) {
	return null;
    }

    @Override
    public T visit(Neg o, U context) {
	return null;
    }

    @Override
    public T visit(Or o, U context) {
	return null;
    }

    @Override
    public T visit(And o, U context) {
	return null;
    }

    @Override
    public T visit(Eq o, U context) {
	return null;
    }

    @Override
    public T visit(GEq o, U context) {
	return null;
    }

    @Override
    public T visit(GT o, U context) {
	return null;
    }

    @Override
    public T visit(LEq o, U context) {
	return null;
    }

    @Override
    public T visit(LT o, U context) {
	return null;
    }

    @Override
    public T visit(NEq o, U context) {
	return null;
    }

    @Override
    public T visit(Not o, U context) {
	return null;
    }

}
