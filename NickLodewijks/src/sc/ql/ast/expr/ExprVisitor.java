package sc.ql.ast.expr;

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
