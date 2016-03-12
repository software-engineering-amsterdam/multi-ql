package eu.bankersen.kevin.ql.typechecker.errors;

import eu.bankersen.kevin.ql.ast.expr.BinaryExpr;
import eu.bankersen.kevin.ql.ast.expr.Expr;
import eu.bankersen.kevin.ql.ast.expr.UnaryExpr;
import eu.bankersen.kevin.ql.ast.expr.math.Div;
import eu.bankersen.kevin.ql.ast.expr.math.Mul;
import eu.bankersen.kevin.ql.ast.stat.ComputedQuestion;
import eu.bankersen.kevin.ql.ast.stat.ElseStatement;
import eu.bankersen.kevin.ql.ast.stat.IFStatement;
import eu.bankersen.kevin.ql.ast.types.QLType;

public class ExprTypeError extends TypeCheckError {

    public ExprTypeError(IFStatement o, QLType expr) {
	super(o.line(), String.format("If-Expression must resolve to Boolean, got %s!", expr));
    }

    public ExprTypeError(ElseStatement o, QLType expr) {
	super(o.line(), String.format("If-Expression must resolve to Boolean, got %s!", expr));
    }

    public ExprTypeError(ComputedQuestion o, QLType q, QLType expr) {
	super(o.line(),
		String.format("Computed Question \"%s\" not compatible, expected %s got %s!", o.name(), q, expr));
    }

    public ExprTypeError(Expr o, QLType lhs, QLType rhs) {
	super(o.line(), String.format("Cannot perform this operation on %s and %s!", lhs, rhs));
    }

    public ExprTypeError(BinaryExpr o, QLType expr) {
	super(o.line(), String.format("Expression must resolve to boolean, got %s!", expr));
    }

    public ExprTypeError(Mul o, QLType lhs, QLType rhs) {
	super(o.line(), String.format("Can not multiply %s by %s!", lhs, rhs));
    }

    public ExprTypeError(Div o, QLType lhs, QLType rhs) {
	super(o.line(), String.format("Can not divide %s by %s!", lhs, rhs));
    }

    public ExprTypeError(UnaryExpr o, QLType expr) {
	super(o.line(), String.format("Expression must be of the number QLType, got %s!", expr));
    }
}