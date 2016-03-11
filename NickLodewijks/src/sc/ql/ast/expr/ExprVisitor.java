package sc.ql.ast.expr;

import sc.ql.ast.expr.Expression.Add;
import sc.ql.ast.expr.Expression.And;
import sc.ql.ast.expr.Expression.BooleanLiteral;
import sc.ql.ast.expr.Expression.Divide;
import sc.ql.ast.expr.Expression.Equals;
import sc.ql.ast.expr.Expression.EqualsNot;
import sc.ql.ast.expr.Expression.GreaterThan;
import sc.ql.ast.expr.Expression.GreaterThanOrEqual;
import sc.ql.ast.expr.Expression.IntegerLiteral;
import sc.ql.ast.expr.Expression.LessThan;
import sc.ql.ast.expr.Expression.LessThanOrEqual;
import sc.ql.ast.expr.Expression.Multiply;
import sc.ql.ast.expr.Expression.Negative;
import sc.ql.ast.expr.Expression.Not;
import sc.ql.ast.expr.Expression.Or;
import sc.ql.ast.expr.Expression.Positive;
import sc.ql.ast.expr.Expression.StringLiteral;
import sc.ql.ast.expr.Expression.Subtract;
import sc.ql.ast.expr.Expression.VariableExpr;

public interface ExprVisitor<T, U> {

	public T visit(Add node, U context);

	public T visit(Subtract node, U context);

	public T visit(Divide node, U context);

	public T visit(Multiply node, U context);

	public T visit(Equals node, U context);

	public T visit(GreaterThanOrEqual node, U context);

	public T visit(GreaterThan node, U context);

	public T visit(LessThanOrEqual node, U context);

	public T visit(LessThan node, U context);

	public T visit(EqualsNot node, U context);

	public T visit(Or node, U context);

	public T visit(And node, U context);

	public T visit(Negative node, U context);

	public T visit(Not node, U context);

	public T visit(Positive node, U context);

	public T visit(VariableExpr node, U context);

	public T visit(BooleanLiteral node, U context);

	public T visit(IntegerLiteral node, U context);

	public T visit(StringLiteral node, U context);

}
