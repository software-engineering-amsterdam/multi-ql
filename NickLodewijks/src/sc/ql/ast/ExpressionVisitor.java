package sc.ql.ast;

import sc.ql.ast.Expression.Add;
import sc.ql.ast.Expression.And;
import sc.ql.ast.Expression.BooleanLiteral;
import sc.ql.ast.Expression.Divide;
import sc.ql.ast.Expression.Equals;
import sc.ql.ast.Expression.EqualsNot;
import sc.ql.ast.Expression.GreaterThan;
import sc.ql.ast.Expression.GreaterThanOrEqual;
import sc.ql.ast.Expression.IntegerLiteral;
import sc.ql.ast.Expression.LessThan;
import sc.ql.ast.Expression.LessThanOrEqual;
import sc.ql.ast.Expression.Multiply;
import sc.ql.ast.Expression.Negative;
import sc.ql.ast.Expression.Not;
import sc.ql.ast.Expression.Or;
import sc.ql.ast.Expression.Positive;
import sc.ql.ast.Expression.StringLiteral;
import sc.ql.ast.Expression.Subtract;
import sc.ql.ast.Expression.VariableExpr;

public interface ExpressionVisitor<T, U> {

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
