package eu.bankersen.kevin.ql.form.ast.expressions.visitors;

import eu.bankersen.kevin.ql.form.ast.expressions.Identifier;
import eu.bankersen.kevin.ql.form.ast.expressions.Literal;
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

public interface Visitor<T, U> {

	T visit(Sub expression, U context);

	T visit(Add expression, U context);

	T visit(Div expression, U context);

	T visit(Mul expression, U context);

	T visit(Pos expression, U context);

	T visit(Neg expression, U context);

	T visit(Or expression, U context);

	T visit(And expression, U context);

	T visit(Eq expression, U context);

	T visit(GEq expression, U context);

	T visit(GT expression, U context);

	T visit(LEq expression, U context);

	T visit(LT expression, U context);

	T visit(NEq expression, U context);

	T visit(Not expression, U context);

	T visit(Literal expression, U context);

	T visit(Identifier expression, U context);

}
