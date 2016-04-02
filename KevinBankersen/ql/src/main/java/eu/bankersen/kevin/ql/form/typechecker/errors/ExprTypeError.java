package eu.bankersen.kevin.ql.form.typechecker.errors;

import eu.bankersen.kevin.ql.form.ast.expressions.Binary;
import eu.bankersen.kevin.ql.form.ast.expressions.Expression;
import eu.bankersen.kevin.ql.form.ast.expressions.Unary;
import eu.bankersen.kevin.ql.form.ast.expressions.math.Div;
import eu.bankersen.kevin.ql.form.ast.expressions.math.Mul;
import eu.bankersen.kevin.ql.form.ast.statements.ComputedQuestion;
import eu.bankersen.kevin.ql.form.ast.statements.ElseStatement;
import eu.bankersen.kevin.ql.form.ast.statements.IFStatement;
import eu.bankersen.kevin.ql.form.ast.types.Type;

public class ExprTypeError extends TypeCheckError {

	public ExprTypeError(IFStatement o, Type expr) {
		super(o.line(), String.format("If-Expression must resolve to Boolean, got %s!", expr));
	}

	public ExprTypeError(ElseStatement o, Type expr) {
		super(o.line(), String.format("If-Expression must resolve to Boolean, got %s!", expr));
	}

	public ExprTypeError(ComputedQuestion o, Type q, Type expr) {
		super(o.line(),
				String.format("Computed Question \"%s\" not compatible, expected %s got %s!", o.name(), q, expr));
	}

	public ExprTypeError(Expression o, Type lhs, Type rhs) {
		super(o.line(), String.format("Cannot perform this operation on %s and %s!", lhs, rhs));
	}

	public ExprTypeError(Binary o, Type expr) {
		super(o.line(), String.format("Expression must resolve to boolean, got %s!", expr));
	}

	public ExprTypeError(Mul o, Type lhs, Type rhs) {
		super(o.line(), String.format("Can not multiply %s by %s!", lhs, rhs));
	}

	public ExprTypeError(Div o, Type lhs, Type rhs) {
		super(o.line(), String.format("Can not divide %s by %s!", lhs, rhs));
	}

	public ExprTypeError(Unary o, Type expr) {
		super(o.line(), String.format("Expression must be of the number QLType, got %s!", expr));
	}
}