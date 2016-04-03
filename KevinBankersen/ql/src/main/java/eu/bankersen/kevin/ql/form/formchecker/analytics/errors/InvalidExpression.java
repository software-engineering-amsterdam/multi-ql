package eu.bankersen.kevin.ql.form.formchecker.analytics.errors;

import eu.bankersen.kevin.ql.form.ast.expressions.Binary;
import eu.bankersen.kevin.ql.form.ast.expressions.Unary;
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
import eu.bankersen.kevin.ql.form.ast.statements.ComputedQuestion;
import eu.bankersen.kevin.ql.form.ast.statements.ElseStatement;
import eu.bankersen.kevin.ql.form.ast.statements.IFStatement;
import eu.bankersen.kevin.ql.form.ast.types.Type;

public class InvalidExpression extends AnalyticsError {

	public InvalidExpression(IFStatement o, Type expr) {
		super(o.line(), String.format("If-Expression must resolve to Boolean, got %s!", expr));
	}

	public InvalidExpression(ElseStatement o, Type expr) {
		super(o.line(), String.format("If-Expression must resolve to Boolean, got %s!", expr));
	}

	public InvalidExpression(ComputedQuestion o, Type q, Type expr) {
		super(o.line(),
				String.format("Computed Question \"%s\" not compatible, expected %s got %s!", o.name(), q, expr));
	}

	public InvalidExpression(Binary o, Type lhs, Type rhs) {
		super(o.line(), String.format("Cannot perform this operation on %s and %s!", lhs, rhs));
	}

	public InvalidExpression(Unary o, Type expr) {
		super(o.line(), String.format("Expression must be of the number QLType, got %s!", expr));
	}

	public InvalidExpression(Sub o, Type lhs, Type rhs) {
		super(o.line(), String.format("Can not subtract %s with %s!", lhs, rhs));
	}

	public InvalidExpression(Add o, Type lhs, Type rhs) {
		super(o.line(), String.format("Can not add %s with %s!", lhs, rhs));
	}

	public InvalidExpression(Mul o, Type lhs, Type rhs) {
		super(o.line(), String.format("Can not multiply %s by %s!", lhs, rhs));
	}

	public InvalidExpression(Div o, Type lhs, Type rhs) {
		super(o.line(), String.format("Can not divide %s by %s!", lhs, rhs));
	}

	public InvalidExpression(Pos o, Type expr) {
		super(o.line(), String.format("Can not apply positive to %s!", expr));
	}

	public InvalidExpression(Neg o, Type expr) {
		super(o.line(), String.format("Can not apply negative to %s!", expr));
	}

	public InvalidExpression(Not o, Type expr) {
		super(o.line(), String.format("Can not apply not to %s!", expr));
	}

	public InvalidExpression(And o, Type lhs, Type rhs) {
		super(o.line(), String.format("Can not perform and on %s and %s!", lhs, rhs));
	}

	public InvalidExpression(Or o, Type lhs, Type rhs) {
		super(o.line(), String.format("Can not perform or on %s and %s!", lhs, rhs));
	}

	public InvalidExpression(Eq o, Type lhs, Type rhs) {
		super(o.line(), String.format("Can not perform equal on %s and %s!!", lhs, rhs));
	}

	public InvalidExpression(GEq o, Type lhs, Type rhs) {
		super(o.line(), String.format("Can not perform greater or equal on %s and %s!!", lhs, rhs));
	}

	public InvalidExpression(GT o, Type lhs, Type rhs) {
		super(o.line(), String.format("Can not perform greater on %s and %s!!", lhs, rhs));
	}

	public InvalidExpression(LEq o, Type lhs, Type rhs) {
		super(o.line(), String.format("Can not perform lower or equal on %s and %s!", lhs, rhs));
	}

	public InvalidExpression(LT o, Type lhs, Type rhs) {
		super(o.line(), String.format("Can not perform lower on %s and %s!", lhs, rhs));
	}

	public InvalidExpression(NEq o, Type lhs, Type rhs) {
		super(o.line(), String.format("Can not perform not equal on %s and %s!", lhs, rhs));
	}
}